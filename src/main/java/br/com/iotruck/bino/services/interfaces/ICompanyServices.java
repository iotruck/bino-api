package br.com.iotruck.bino.services.interfaces;

import br.com.iotruck.bino.entity.Company;

public interface ICompanyServices  {
    Boolean create(Company company);
    Company findByCnpj(String cnpj);
}
