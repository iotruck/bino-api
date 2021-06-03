package br.com.iotruck.bino.services.interfaces;

import br.com.iotruck.bino.entity.Company;

import java.util.Optional;

public interface ICompanyServices  {
    Boolean create(Company company);
    Company findByCnpj(String cnpj);
    Optional<Company> findCompanyById(int id);
}
