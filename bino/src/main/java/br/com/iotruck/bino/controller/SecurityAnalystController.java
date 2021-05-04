package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.dto.SecurityAnalystDto;
import br.com.iotruck.bino.entity.SecurityAnalyst;
import br.com.iotruck.bino.entity.SecurityAnalystLogin;
import br.com.iotruck.bino.services.SecurityAnalystServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/securityanalyst")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@Api("Bino API REST Security Analyst")
public class SecurityAnalystController {


    final SecurityAnalystServices services;

    @GetMapping("{id}")
    @ApiOperation("Retorna um analista buscando por id")
    public ResponseEntity getById(@PathVariable int id) {
        if (id <= 0)
            return ResponseEntity.badRequest().body("O id não pode ser menor ou igual a zero");

        return ResponseEntity.ok(services.getById(id));
    }

    @PostMapping("/login")
    @ApiOperation("Login")
    public ResponseEntity login(@RequestBody SecurityAnalystLogin analystLogin) {
        String email = analystLogin.getEmail();
        String password = analystLogin.getPassword();

        SecurityAnalystDto analyst = services.login(email, password);

        return analyst != null ? ResponseEntity.ok().body(analyst) : ResponseEntity.status(401).body("Usuario Invalido");
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de analista")
    public ResponseEntity getAll() {
        List<SecurityAnalystDto> analysts = services.getAll();
        if (analysts.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(analysts);
    }

    @PostMapping
    @ApiOperation("Cria uma analista")
    public ResponseEntity postAnalyst(@RequestBody @Valid SecurityAnalyst analyst) {
        services.create(analyst);
        return ResponseEntity.status(201).build();

    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um analista")
    public ResponseEntity putAnalyst(@PathVariable int id,
                                     @RequestBody @Valid SecurityAnalyst analyst) {

        if (services.update(id, analyst))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove um analista")
    public ResponseEntity deleteAnalyst(@PathVariable int id) {
        if (services.delete(id))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }
}
