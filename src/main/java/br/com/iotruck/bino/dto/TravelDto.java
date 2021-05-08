package br.com.iotruck.bino.dto;

import br.com.iotruck.bino.entity.*;

import java.time.LocalDate;

public class TravelDto {


    private Integer id;
    private String codigo;
    private Location location;
    private String description;
    private LocalDate dateTravel;
    private TruckerDto trucker;
    private TruckDto truck;
    private SecurityAnalystDto analyst;

    public TravelDto(Travel travel) {
        this.id = travel.getId();
        this.codigo = travel.getCodigo();
        this.location = travel.getLocation();
        this.description = travel.getDescription();
        this.dateTravel = travel.getDateTravel();
        this.trucker = new TruckerDto(travel.getTrucker());
        this.truck = new TruckDto(travel.getTruck());
        this.analyst = new SecurityAnalystDto(travel.getAnalyst());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateTravel() {
        return dateTravel;
    }

    public void setDateTravel(LocalDate dateTravel) {
        this.dateTravel = dateTravel;
    }

    public TruckerDto getTrucker() {
        return trucker;
    }

    public void setTrucker(TruckerDto trucker) {
        this.trucker = trucker;
    }

    public TruckDto getTruck() {
        return truck;
    }

    public void setTruck(TruckDto truck) {
        this.truck = truck;
    }

    public SecurityAnalystDto getAnalyst() {
        return analyst;
    }

    public void setAnalyst(SecurityAnalystDto analyst) {
        this.analyst = analyst;
    }
}
