package br.com.iotruck.bino.dto;

import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.entity.enuns.TruckType;


public class TruckDto {


    private int id;
    private String name;
    private String truckBrand;
    private TruckType truckType;

    public TruckDto(Truck truck) {
        this.id = truck.getId();
        this.name = truck.getName();
        this.truckBrand = truck.getTruckBrand();
        this.truckType = truck.getTruckType();
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

    public String getTruckBrand() {
        return truckBrand;
    }

    public void setTruckBrand(String truckBrand) {
        this.truckBrand = truckBrand;
    }

    public TruckType getTruckType() {
        return truckType;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }
}
