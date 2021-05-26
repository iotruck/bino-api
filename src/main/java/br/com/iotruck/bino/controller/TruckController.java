package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.dto.TruckDto;
import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.repository.ICompanyRepository;
import br.com.iotruck.bino.repository.ITravelRepository;
import br.com.iotruck.bino.repository.ITruckRepository;
import br.com.iotruck.bino.repository.ITruckerRepository;
import br.com.iotruck.bino.services.TruckServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/truck")
@CrossOrigin(origins = "*")
//@AllArgsConstructor
@RestController
@Api("Bino API REST Truck")
public class TruckController {

    @Autowired
    private ITruckRepository truckRepository;

    @Autowired
    private ICompanyRepository companyRepository;

    @Autowired
    TruckServices services;

    @GetMapping("/{id}")
    @ApiOperation("Retorna um caminhão buscando por id")
    public ResponseEntity getById(@PathVariable int id) {
        if (id <= 0)
            return ResponseEntity.badRequest().body("O id não pode ser menor ou igual a zero");

        return ResponseEntity.of(services.getById(id));
    }

    @GetMapping("/company/{id}")
    @ApiOperation("Retorna uma lista de caminhões")
    public ResponseEntity getAll(@PathVariable Integer id) {
        List<TruckDto> truckList = services.findAllByCompanyId(id);
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

    @GetMapping("/score/{id}")
    public ResponseEntity getCount(@PathVariable Integer id) {
        if (companyRepository.existsById(id)) {
            return ResponseEntity.ok().body(truckRepository.countByCompanyId(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
