package br.com.iotruck.bino.controller;


import br.com.iotruck.bino.dto.TravelDto;
import br.com.iotruck.bino.entity.Travel;
import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.entity.Trucker;
import br.com.iotruck.bino.entity.enuns.TravelStatus;
import br.com.iotruck.bino.entity.enuns.TruckStatus;
import br.com.iotruck.bino.entity.enuns.TruckerStatus;
import br.com.iotruck.bino.extensions.FilaObj;
import br.com.iotruck.bino.repository.IMessageRepository;
import br.com.iotruck.bino.repository.ITravelRepository;
import br.com.iotruck.bino.repository.ITruckRepository;
import br.com.iotruck.bino.repository.ITruckerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequestMapping("/travel")
@CrossOrigin(origins = "*")
@RestController
@Api("Bino API REST Travel")
public class TravelController {

    @Autowired
    private ITravelRepository repository;

    @Autowired
    private ITruckerRepository truckerRepository;

    @Autowired
    private ITruckRepository truckRepository;

    @Autowired
    private IMessageRepository messageRepository;

    FilaObj<Travel> filaObj = new FilaObj<>(10);

    List<Travel> travelList = new ArrayList<>();

    @GetMapping("/trucker/{id}")
    @ApiOperation("Listagem de viagens pelo caminhoneiro")
    public ResponseEntity getTravelsTrucker(@PathVariable Integer id) {
        List<TravelDto> travels = repository.findAllByTruckertId(id);

        if (travels.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(travels);
    }

    @GetMapping("/analyst/{id}")
    @ApiOperation("Listagem de viagens pelo analista")
    public ResponseEntity getTravels(@PathVariable Integer id) {
        List<TravelDto> travels = repository.findAllByAnalystId(id);

        if (travels.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(travels);
    }

    @GetMapping("/search")
    @ApiOperation("Listagem de viagens pelo codigo")
    public ResponseEntity getTravelsByCode(@RequestParam String codigo) {

        List<TravelDto> travels = repository.findByCodigoLike(codigo);

        if (travels.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(travels);
    }

    @GetMapping("trucker/{id_trucker}/{id}")
    @ApiOperation("Retorna uma viagem pelo caminhoneiro")
    public ResponseEntity getTravelTrucker(@PathVariable int id_trucker, @PathVariable int id) {
        if (id <= 0)
            return ResponseEntity.status(400).body("O id não pode ser menor ou igual a zero");

        Travel travel = repository.findByTruckerIdAndId(id_trucker, id);

        if (travel != null)
            return ResponseEntity.status(200).body(travel);

        return ResponseEntity.status(404).build();

    }

    @GetMapping("analyst/{id_analyst}/{id}")
    @ApiOperation("Retorna uma viagem pelo analista")
    public ResponseEntity getTravels(@PathVariable int id_analyst, @PathVariable int id) {
        if (id <= 0)
            return ResponseEntity.status(400).body("O id não pode ser menor ou igual a zero");

        Travel travel = repository.findByAnalystIdAndId(id_analyst, id);

        if (travel != null)
            return ResponseEntity.status(200).body(travel);

        return ResponseEntity.status(404).build();

    }

    @PostMapping
    @ApiOperation("Cria uma viagem")
    public ResponseEntity postTravel(@Valid @RequestBody Travel travel) {
        Integer idTruck = travel.getTruck().getId();
        Integer idTrucker = travel.getTrucker().getId();

        Optional<Truck> truck = truckRepository.findById(idTruck);
        Optional<Trucker> trucker = truckerRepository.findById(idTrucker);

        if (trucker.isPresent() && truck.isPresent()) {

            Travel travelCheck = repository.findTravelByTruckerAndTruckAndDateTravel(
                    idTrucker,
                    idTruck,
                    travel.getDateTravel().toString());
            if (travelCheck == null){
                truck.get().setStatus(TruckStatus.BUSY);
                trucker.get().setStatus(TruckerStatus.BUSY);
                repository.save(travel);
                truckerRepository.save(trucker.get());
                truckRepository.save(truck.get());
                return ResponseEntity.status(201).build();

            }else {
                return ResponseEntity.status(400).body("Caminhoneiro ou Caminhão em uso, por favor selecione outro para essa viagem");
            }
        } else {

            return ResponseEntity.status(400).build();

        }
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza uma viagem")
    public ResponseEntity putTravel(@PathVariable int id, @RequestBody @Valid Travel travel) {

        if (id <= 0)
            return ResponseEntity.status(400).body("O id não pode ser menor ou igual a zero");

        if (repository.existsById(id)) {

            travel.setId(id);

            repository.save(travel);

            return ResponseEntity.status(200).build();

        } else {

            return ResponseEntity.status(400).build();

        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove uma viagem")
    public ResponseEntity deleteTravel(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("get-assync/{id}")
    @ApiOperation("Armazena a requisição em uma fila")
    public ResponseEntity getAssyncTravel(@PathVariable int id){
        Optional<Travel> travel = repository.findById(id);
            if (travel.isPresent()) {
                filaObj.insert(travel.get());
                return ResponseEntity.ok().body(travel.get().getId());
            }
            return ResponseEntity.status(404).build();
    }

    @GetMapping("get-list")
    @ApiOperation("Verifica se existe algo na fila e adiciona para um List")
    public ResponseEntity getListTravel(){
            if (!filaObj.isEmpty()){
                travelList.add(filaObj.poll());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(404).body("Não há nada a tratar");
    }

    @GetMapping("treatment-assync/{id}")
    @ApiOperation("Consulta se já terminou o tratamento de alguma requisação assíncrona")
    public ResponseEntity getFinishAssinc(@PathVariable int id){
            for (int i = 0; i < travelList.size(); i++){
                Travel travel = travelList.get(i);
                if (travel.getId() == id){
                    travelList.remove(i);
                    return ResponseEntity.ok().body(travel);
                }
            }
            return ResponseEntity.status(404).body("Requisição não processada");
    }

    @GetMapping("trucker/latter/{id}")
    @ApiOperation("Retorna a última viagem do motorista")
    public ResponseEntity getLatterTravelTrucker(@PathVariable int id) {

        Travel travel = repository.findFirstByTruckerIdAndStatus(id, TravelStatus.ACTIVE);

        if (travel != null) {
            return ResponseEntity.status(200).body(travel);
        }

        LocalDate dateNow = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        Travel travelToday = repository.findFirstByTruckerIdAndDateTravel(id, dateNow);

        if(travelToday != null) {
            return ResponseEntity.status(200).body(travelToday);
        } else {
            return ResponseEntity.status(204).build();
        }

    }
}
