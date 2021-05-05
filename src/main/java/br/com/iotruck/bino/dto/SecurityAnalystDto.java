package br.com.iotruck.bino.dto;


import br.com.iotruck.bino.entity.SecurityAnalyst;

public class SecurityAnalystDto {

    private int id;
    private String name;
    private String email;

    public SecurityAnalystDto(SecurityAnalyst analyst) {
        this.id = analyst.getId();
        this.name = analyst.getName();
        this.email = analyst.getEmail();
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
}
