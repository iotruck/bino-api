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

    @JoinColumn(name = "fk_destiny")
    @OneToOne(cascade= CascadeType.ALL)
    private Location destiny;

    @JoinColumn(name = "fk_current_truck_position")
    @OneToOne(cascade= CascadeType.ALL)
    private Location currentTruckPosition;

    @JoinColumn(name = "fk_feed")
    @OneToOne(cascade= CascadeType.ALL)
    private Feed feed;

    @NotBlank
    private String description;

    @FutureOrPresent
    private LocalDate dateTravel;

    @JoinColumn(name = "id_trucker")
    @ManyToOne
    private Trucker trucker;

    @JoinColumn(name = "id_truck")
    @ManyToOne
    private Truck truck;

    @JoinColumn(name = "id_analyst")
    @ManyToOne
    private SecurityAnalyst analyst;

    private Double estimatedValue;

    private TravelStatus status;

    public SecurityAnalyst getAnalyst() {
        return analyst;
    }

    public void setAnalyst(SecurityAnalyst analyst) {
        this.analyst = analyst;
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

    public Location getDestiny() {
        return destiny;
    }

    public void setDestiny(Location destiny) {
        this.destiny = destiny;
    }

    public Location getCurrentTruckPosition() {
        return currentTruckPosition;
    }

    public void setCurrentTruckPosition(Location currentTruckPosition) {
        this.currentTruckPosition = currentTruckPosition;
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

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
