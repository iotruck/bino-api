package br.com.iotruck.bino.dto;

import br.com.iotruck.bino.entity.SecurityAnalyst;

public class SecurityAnalystDto {

    private Integer id;
    private String name;
    private String email;
    private Boolean admin;
    private CompanyDto company;

    public SecurityAnalystDto(SecurityAnalyst analyst) {
        this.id = analyst.getId();
        this.name = analyst.getName();
        this.email = analyst.getEmail();
        this.admin = analyst.getAdmin();
        this.company = new CompanyDto(analyst.getCompany());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

}
