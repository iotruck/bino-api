package br.com.iotruck.bino.dto;

import br.com.iotruck.bino.entity.Message;

import java.time.LocalDateTime;

public class MessageDto {
    private Integer id;
    private String content;
    private String sender;
    private LocalDateTime dateTimeMessage;

    public MessageDto(Message message){
        this.id = message.getId();
        this.content = message.getContent();
        this.sender = message.getSender();
        this.dateTimeMessage = message.getDateTimeMessage();
    }

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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getDateTimeMessage() {
        return dateTimeMessage;
    }

    public void setDateTimeMessage(LocalDateTime dateTimeMessage) {
        this.dateTimeMessage = dateTimeMessage;
    }
}
