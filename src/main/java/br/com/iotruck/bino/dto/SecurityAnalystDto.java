package br.com.iotruck.bino.dto;


import br.com.iotruck.bino.entity.SecurityAnalyst;

public class SecurityAnalystDto {

    private int id;
    private String name;
    private String email;
    private Boolean admin;

    public SecurityAnalystDto(SecurityAnalyst analyst) {
        this.id = analyst.getId();
        this.name = analyst.getName();
        this.email = analyst.getEmail();
        this.admin = analyst.getAdmin();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
