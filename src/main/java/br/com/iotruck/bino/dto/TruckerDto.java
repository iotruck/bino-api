package br.com.iotruck.bino.dto;

import br.com.iotruck.bino.entity.Trucker;


public class TruckerDto {

    private int id;
    private String name;
    private String cpf;
    private String cnh;

    public TruckerDto(Trucker trucker) {
        this.id = trucker.getId();
        this.name = trucker.getName();
        this.cpf = trucker.getCpf();
        this.cnh = trucker.getCnh();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }


}
