package br.com.iotruck.bino.entity;

import br.com.iotruck.bino.entity.enuns.TravelStatus;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 20)
    private String codigo;

    @JoinColumn(name = "fk_location")
    @OneToOne(cascade=CascadeType.PERSIST)
    private Location location;

    @NotBlank
    private String description;

    @FutureOrPresent
    private LocalDate dateTravel;

    @JoinColumn(name = "fk_trucker")
    @ManyToOne
    private Trucker trucker;

    @JoinColumn(name = "fk_truck")
    @ManyToOne
    private Truck truck;

    private Double estimatedValue;

    private TravelStatus status;

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

    public Trucker getTrucker() {
        return trucker;
    }

    public void setTrucker(Trucker trucker) {
        this.trucker = trucker;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(Double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public TravelStatus getStatus() {
        return status;
    }

    public void setStatus(TravelStatus status) {
        this.status = status;
    }
}
