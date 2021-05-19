package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.entity.Location;
import br.com.iotruck.bino.repository.ILocationRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/location")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@Api("Bino API REST Location")
public class LocationController {

    @Autowired
    private ILocationRepository respository;

    @GetMapping("/{id}")
    @ApiOperation("Retorna uma localização")
    public ResponseEntity getLocation(@PathVariable Integer id) {
        Optional<Location> location = respository.findById(id);

        if (location.isPresent())
            return ResponseEntity.status(200).body(location);

        return ResponseEntity.status(204).build();

    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza uma Localização")
    public ResponseEntity putTravel(@PathVariable int id, @RequestBody @Valid Location location) {

        if (id <= 0)
            return ResponseEntity.status(400).body("O id não pode ser menor ou igual a zero");

        if (respository.existsById(id)) {

            location.setId(id);

            respository.save(location);

            return ResponseEntity.status(200).build();

        } else {

            return ResponseEntity.status(400).build();

        }
    }
}
