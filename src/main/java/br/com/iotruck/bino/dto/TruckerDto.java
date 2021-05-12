package br.com.iotruck.bino.dto;

import br.com.iotruck.bino.entity.Trucker;

import java.time.LocalDate;


public class TruckerDto {

    private int id;
    private String name;
    private String cpf;
    private String cnh;
    private LocalDate birthDate;
    private String phoneNumber;

    public TruckerDto(Trucker trucker) {
        this.id = trucker.getId();
        this.name = trucker.getName();
        this.cpf = trucker.getCpf();
        this.cnh = trucker.getCnh();
        this.birthDate = trucker.getBirthDate();
        this.phoneNumber = trucker.getPhoneNumber();
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
