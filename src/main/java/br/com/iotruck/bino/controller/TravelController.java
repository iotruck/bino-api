package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.entity.Travel;
import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.entity.Trucker;
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
import javax.validation.constraints.Pattern;
import java.util.Optional;


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

    @PostMapping("/{id_trucker}/{id_truck}")
    @ApiOperation("Cria uma viagem")
    public ResponseEntity postTravel(@PathVariable int id_trucker,
                                     @PathVariable int id_truck,
                                     @Valid @RequestBody Travel travel) {

        Optional<Trucker> trucker = truckerRepository.findById(id_trucker);
        Optional<Truck> truck = truckRepository.findById(id_truck);

        if(trucker.isPresent() && truck.isPresent()) {
            travel.setTrucker(trucker.get());
            travel.setTruck(truck.get());
            respository.save(travel);
            return ResponseEntity.status(201).build();
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
