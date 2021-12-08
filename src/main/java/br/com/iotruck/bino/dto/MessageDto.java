package br.com.iotruck.bino.dto;

import br.com.iotruck.bino.entity.Message;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MessageDto {
    private Integer id;
    private String content;
    private String sender;
    private String dateTimeMessage;

    public MessageDto(Message message) {
        this.id = message.getId();
        this.content = message.getContent();
        this.sender = message.getSender();
        this.dateTimeMessage = formateDate(message.getDateTimeMessage());
    }

    private String formateDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println(String.format("data normal: %s",date.toString()));
        System.out.println(String.format("data formatada: %s",date.atZone(ZoneId.of("America/Sao_Paulo")).format(formatter).toString()));
        return date.atZone(ZoneId.of("America/Sao_Paulo")).format(formatter).toString();
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

    public String getDateTimeMessage() {
        return dateTimeMessage;
    }

    public void setDateTimeMessage(String dateTimeMessage) {
        this.dateTimeMessage = dateTimeMessage;
    }
}
