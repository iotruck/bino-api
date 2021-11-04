package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.dto.TravelDto;
import br.com.iotruck.bino.entity.Message;
import br.com.iotruck.bino.repository.IMessageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/feed")
@CrossOrigin(origins = "*")
@RestController
@Api("Bino API REST feed")
public class MessageController {

    @Autowired
    IMessageRepository messageRepository;

    @GetMapping("/message/{idTravel}/{qtd}")
    @ApiOperation("Listagem de mensagens")
    public ResponseEntity getMessage(@PathVariable Integer idTravel, @PathVariable Integer qtd) {

        Integer countRepository = messageRepository.countByTravelId(idTravel);
        Integer diffCount = countRepository - qtd;

        if(diffCount < 0) {
            return ResponseEntity.status(400).build();
        } else if (diffCount.equals(0)) {
            return ResponseEntity.status(204).build();
        }

        List<Message> messages = messageRepository.findAllByTravelIdOrderByDateTimeMessageDesc(idTravel, diffCount);

        if (messages.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(messages);
    }

    @PostMapping("/message")
    @ApiOperation("envia uma mensagen")
    public ResponseEntity postMessage(@RequestBody Message message) {
        messageRepository.save(message);
        return ResponseEntity.status(201).build();
    }

}
