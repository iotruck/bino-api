package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.dto.MessageDto;
import br.com.iotruck.bino.entity.Message;
import br.com.iotruck.bino.repository.IMessageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/feed")
@CrossOrigin(origins = "*")
@RestController
@Api("Bino API REST feed")
public class MessageController {

    @Autowired
    IMessageRepository messageRepository;

    @GetMapping("/message/{idTravel}")
    @ApiOperation("Listagem de mensagens")
    public ResponseEntity getMessage(@PathVariable Integer idTravel, @RequestParam(required = false) Integer qtd) {

        List<Message> messages;

        if (qtd == null) {

            messages = messageRepository.findAllByTravelIdOrderByDateTimeMessage(idTravel);

            return ResponseEntity.status(200).body(messages.stream().map(s -> new MessageDto(s)).collect(Collectors.toList()));
        }

        Integer diff = messageRepository.countByTravelId(idTravel) - qtd;

        if (diff < 0) {
            return ResponseEntity.status(400).build();
        } else if (diff.equals(0)) {
            return ResponseEntity.status(204).build();
        }

        messages = messageRepository.findAllByTravelIdOrderByDateTimeMessageDesc(idTravel, PageRequest.of(0, diff));

        List<Message> sortedList = messages.stream()
                .sorted(Comparator.comparing(Message::getDateTimeMessage))
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(sortedList.stream().map(s -> new MessageDto(s)).collect(Collectors.toList()));
    }

    @PostMapping("/message")
    @ApiOperation("envia uma mensagen")
    public ResponseEntity postMessage(@RequestBody Message message) {
        messageRepository.save(message);
        return ResponseEntity.status(201).build();
    }

}
