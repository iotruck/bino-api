package br.com.iotruck.bino.services;

import br.com.iotruck.bino.entity.Company;
import br.com.iotruck.bino.repository.ICompanyRepository;
import br.com.iotruck.bino.services.interfaces.ICompanyServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServices implements ICompanyServices {

    private final ICompanyRepository repository;


    public Boolean create(Company company) {
        repository.save(company);
        return true;
    }

}
