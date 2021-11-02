package br.com.iotruck.bino.entity;

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
    @JoinColumn(name = "id_feed")
    @ManyToOne
    private Feed feed;

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

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
