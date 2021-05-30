package br.com.iotruck.bino.entity;

import br.com.iotruck.bino.entity.enuns.FuelType;
import br.com.iotruck.bino.entity.enuns.TruckStatus;
import br.com.iotruck.bino.entity.enuns.TruckType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    @NotBlank
    @Size(min = 2, max = 100)
    private String truckBrand;
    @NotBlank
    @Size(min = 6, max = 110)
    private String licensePlace;
    @NotNull
    private TruckType truckType;
    @NotNull
    private FuelType fuelType;

    private TruckStatus status = TruckStatus.FREE;

    @NotNull
    @JoinColumn(name = "id_company")
    @ManyToOne
    private Company company;

}
