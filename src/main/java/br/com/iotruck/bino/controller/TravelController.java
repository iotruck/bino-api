package br.com.iotruck.bino.controller;


import br.com.iotruck.bino.dto.TravelDto;
import br.com.iotruck.bino.entity.Travel;
import br.com.iotruck.bino.repository.ITravelRespository;
import br.com.iotruck.bino.repository.ITruckRepository;
import br.com.iotruck.bino.repository.ITruckerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/travel")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@Api("Bino API REST Travel")
public class TravelController {

    @Autowired
    private ITravelRespository respository;

    @Autowired
    private ITruckerRepository truckerRepository;

    @Autowired
    private ITruckRepository truckRepository;

        @GetMapping("/analyst/{id}")
    @ApiOperation("Listagem de viagens")
    public ResponseEntity getTravels(@PathVariable Integer id) {
        List<TravelDto> travels = respository.findAllByAnalystId(id);

        if (travels.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(travels);
    }

    @GetMapping("/search")
    @ApiOperation("Listagem de viagens pelo codigo")
    public ResponseEntity getTravelsByCode(@RequestParam String codigo) {

        List<TravelDto> travels = respository.findByCodigoLike(codigo);

        if (travels.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(travels);
    }

    @GetMapping("/{id_analyst}/{id}")
    @ApiOperation("Retorna uma viagem")
    public ResponseEntity getTravels(@PathVariable int id_analyst, @PathVariable int id) {
        if (id <= 0)
            return ResponseEntity.status(400).body("O id não pode ser menor ou igual a zero");

        Travel travel = respository.findByAnalystIdAndId(id_analyst, id);

        if (travel != null)
            return ResponseEntity.status(200).body(travel);

        return ResponseEntity.status(404).build();

    }

    @PostMapping
    @ApiOperation("Cria uma viagem")
    public ResponseEntity postTravel(@Valid @RequestBody Travel travel) {
        Integer idTruck = travel.getTruck().getId();

        Integer idTrucker = travel.getTrucker().getId();

        if (truckerRepository.existsById(idTrucker) && truckRepository.existsById(idTruck)) {

            respository.save(travel);

            return ResponseEntity.status(201).build();

        } else {

            return ResponseEntity.status(400).build();

        }
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza uma viagem")
    public ResponseEntity putTravel(@PathVariable int id, @RequestBody @Valid Travel travel) {

        if (id <= 0)
            return ResponseEntity.status(400).body("O id não pode ser menor ou igual a zero");

        if (respository.existsById(id)) {

            travel.setId(id);

            respository.save(travel);

            return ResponseEntity.status(200).build();

        } else {

            return ResponseEntity.status(400).build();

        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove uma viagem")
    public ResponseEntity deleteTravel(@PathVariable int id) {
        if (respository.existsById(id)) {
            respository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}
