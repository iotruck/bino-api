package br.com.iotruck.bino.dto;

import br.com.iotruck.bino.entity.Company;

public class CompanyDto {

    private Integer id;

    private String name;

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
