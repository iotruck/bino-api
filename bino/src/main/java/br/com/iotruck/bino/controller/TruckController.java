package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.services.TruckServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/truck")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@Api("Bino API REST Truck")
public class TruckController {

    final TruckServices services;

    @GetMapping("/{id}")
    @ApiOperation("Retorna um caminhão buscando por id")
    public ResponseEntity getById(@PathVariable int id) {
        if (id <= 0)
            return ResponseEntity.badRequest().body("O id não pode ser menor ou igual a zero");

        return ResponseEntity.of(services.getById(id));
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de caminhões")
    public ResponseEntity getAll() {
        List<Truck> truckList = services.getAll();
        if (truckList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(truckList);
    }

    @PostMapping
    @ApiOperation("Cria um caminhão")
    public ResponseEntity postTruck(@RequestBody @Valid Truck truck) {
        services.create(truck);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um caminhão")
    public ResponseEntity putTruck(@PathVariable int id,
                                   @RequestBody @Valid Truck truck) {
        if (services.update(id, truck))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove um caminhão")
    public ResponseEntity deleteTruck(@PathVariable int id) {
        if (services.delete(id))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }
}
