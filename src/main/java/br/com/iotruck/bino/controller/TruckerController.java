package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.entity.Trucker;
import br.com.iotruck.bino.services.TruckerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/trucker")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@Api("Bino API REST Truck")
public class TruckerController {

    final TruckerService services;

    @GetMapping("/{id}")
    @ApiOperation("Retorna um caminhoneiro buscando por id")
    public ResponseEntity getById(@PathVariable int id) {
        if (id <= 0)
            return ResponseEntity.badRequest().body("O id não pode ser menor ou igual a zero");

        return ResponseEntity.of(services.getById(id));
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de caminhoneiro")
    public ResponseEntity getAll() {
        List<Trucker> truckerList = services.getAll();
        if (truckerList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(truckerList);
    }

    @PostMapping
    @ApiOperation("Cria um caminhoneiro")
    public ResponseEntity postTrucker(@RequestBody @Valid Trucker trucker) {
        services.create(trucker);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um caminhoneiro")
    public ResponseEntity putTrucker(@PathVariable int id,
                                     @RequestBody @Valid Trucker trucker) {

        if (services.update(id, trucker))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove um caminhoneiro")
    public ResponseEntity deleteTrucker(@PathVariable int id) {
        if (services.delete(id))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }
}
