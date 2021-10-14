package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.dto.SecurityAnalystDto;
import br.com.iotruck.bino.dto.TruckerDto;
import br.com.iotruck.bino.entity.SecurityAnalystLogin;
import br.com.iotruck.bino.entity.Trucker;
import br.com.iotruck.bino.entity.TruckerLogin;
import br.com.iotruck.bino.extensions.PilhaObj;
import br.com.iotruck.bino.extensions.ReadFile;
import br.com.iotruck.bino.repository.ICompanyRepository;
import br.com.iotruck.bino.repository.ITruckerRepository;
import br.com.iotruck.bino.services.TruckServices;
import br.com.iotruck.bino.services.TruckerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/trucker")
@CrossOrigin(origins = "*")
//@AllArgsConstructor
@RestController
@Api("Bino API REST Truck")
public class TruckerController {

    @Autowired
    private ITruckerRepository truckerRepository;

    @Autowired
    private ICompanyRepository companyRepository;

    PilhaObj<Trucker> pilhaTrucker = new PilhaObj<>(999);

    @Autowired
    TruckerService services;

    @Autowired
    TruckServices truckServices;

    @GetMapping("/{id}")
    @ApiOperation("Retorna um caminhoneiro buscando por id")
    public ResponseEntity getById(@PathVariable int id) {
        if (id <= 0)
            return ResponseEntity.badRequest().body("O id não pode ser menor ou igual a zero");

        return ResponseEntity.of(services.getById(id));
    }

    @GetMapping("/cpf/{cpf}")
    @ApiOperation("Retorna um caminhoneiro buscando por cpf")
    public ResponseEntity getCpf(@PathVariable String cpf) {

        Trucker trucker = truckerRepository.findByCpf(cpf);

        return trucker != null ? ResponseEntity.status(200).body(trucker) : ResponseEntity.status(404).build();

    }

    @GetMapping("/company/{id}")
    @ApiOperation("Retorna uma lista de caminhoneiro")
    public ResponseEntity getAll(@PathVariable Integer id) {
        List<TruckerDto> truckerList = services.findAllByCompanyId(id);
        if (truckerList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(truckerList);
    }

    @PostMapping
    @ApiOperation("Cria um caminhoneiro")
    public ResponseEntity postTrucker(@RequestBody @Valid Trucker trucker) {
        services.create(trucker);
        pilhaTrucker.push(trucker);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um caminhoneiro")
    public ResponseEntity putTrucker(@PathVariable int id,
                                     @RequestBody @Valid Trucker trucker) {
        Optional<Trucker> truckerPilha = truckerRepository.findById(id);

        if (truckerPilha.isPresent()) {
            pilhaTrucker.push(truckerPilha.get().setValue(truckerPilha.get()));
        }

        if (services.update(id, trucker))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove um caminhoneiro")
    public ResponseEntity deleteTrucker(@PathVariable Integer id) {
        if (services.delete(id))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/score/{id}")
    public ResponseEntity getCount(@PathVariable Integer id) {
        if (companyRepository.existsById(id)) {
            return ResponseEntity.ok().body(truckerRepository.countByCompanyId(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/undo-update")
    public ResponseEntity getUndoUpdate() {
        if (!pilhaTrucker.isEmpty()) {
            Trucker trucker = pilhaTrucker.pop();
            return putTrucker(trucker.getId(), trucker);
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/upload")
    @ApiOperation("Endpoint para upload de dados")
    public ResponseEntity enviar(@RequestParam MultipartFile arquivo) throws IOException {

        ReadFile readFile = new ReadFile(services,truckServices);

        readFile.reader(arquivo);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    @ApiOperation("Login caminhoneiro")
    public ResponseEntity login(@RequestBody TruckerLogin truckerLogin) {
        String email = truckerLogin.getEmail();
        String password = truckerLogin.getPassword();

        TruckerDto trucker = services.login(email, password);

        return trucker != null ? ResponseEntity.ok().body(trucker) : ResponseEntity.status(401).body("Usuario Invalido");
    }
}
