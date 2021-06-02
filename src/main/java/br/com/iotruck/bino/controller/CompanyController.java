package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.assistant.CompanyAssistant;
import br.com.iotruck.bino.entity.Company;
import br.com.iotruck.bino.entity.SecurityAnalyst;
import br.com.iotruck.bino.services.CompanyServices;
import br.com.iotruck.bino.services.SecurityAnalystServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/company")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@Api("Bino API REST Security Analyst")
public class CompanyController {

    final SecurityAnalystServices analystServices;

    final CompanyServices companyServices;


    @GetMapping("/{id}")
    public ResponseEntity getCompanyByAdress(@PathVariable int id) {
        Optional<Company> company = companyServices.findCompanyById(id);
        if (company.isPresent()) {
            return ResponseEntity.status(200).body(company);
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @PostMapping
    @ApiOperation("Cria uma empresa e um analista admin")
    public ResponseEntity postAnalyst(@RequestBody @Valid CompanyAssistant assistant) {

        if (assistant != null) {
            Company company = new Company();
            company.setName(assistant.getName());
            company.setEmail(assistant.getEmail());
            company.setCnpj(assistant.getCnpj());
            company.setLocation(assistant.getLocation());
            company.setSubscriptions(assistant.getSubscriptions());
            companyServices.create(company);

            SecurityAnalyst analyst = new SecurityAnalyst();
            analyst.setEmail(assistant.getEmail());
            analyst.setPassword(assistant.getPassword());
            analyst.setName(assistant.getName());
            analyst.setAdmin(true);

            analyst.setCompany(companyServices.findByCnpj(company.getCnpj()));

            analystServices.create(analyst);

            return ResponseEntity.status(201).build();
        }

        return ResponseEntity.status(400).build();


    }
}
