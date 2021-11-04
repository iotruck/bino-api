package br.com.iotruck.bino.entity;

import br.com.iotruck.bino.controller.TravelController;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String content;

    private LocalDateTime dateTimeMessage = LocalDateTime.now();

    private String sender;

    @NotNull
    @JoinColumn(name = "id_travel")
    @ManyToOne
    private Travel travel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTimeMessage() {
        return dateTimeMessage;
    }

    public void setDateTimeMessage(LocalDateTime dateTimeMessage) {
        this.dateTimeMessage = dateTimeMessage;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Travel getTravel() {
        return travel;
    }
    public void setTravel(Travel travel) {
        this.travel = travel;
    }
}
