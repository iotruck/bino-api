package br.com.iotruck.bino.controller;

import br.com.iotruck.bino.entity.Feed;
import br.com.iotruck.bino.entity.Message;
import br.com.iotruck.bino.repository.IFeedRepository;
import br.com.iotruck.bino.repository.IMessageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/feed")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@Api("Bino API REST Security Analyst")
public class FeedController {

    @Autowired
    IFeedRepository feedRepository;

    @Autowired
    IMessageRepository messageRepository;

    @GetMapping("/{id}")
    @ApiOperation("Retorna um feed pelo id da viagem")
    public ResponseEntity getFeed(@PathVariable Integer id) {

        Optional<Feed> feed = feedRepository.findById(id);

        return feed.isPresent() ? ResponseEntity.status(200).body(feed.get()) : ResponseEntity.status(404).build();
    }

    @GetMapping("/message/{id}")
    @ApiOperation("Retorna uma lista de mensagens de um feed")
    public ResponseEntity getFeedMessages(@PathVariable Integer id) {

        List<Message> messageList = messageRepository.findAllByFeedIdOrderByDateTimeMessageDesc(id);

        if (messageList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(messageList);
    }

    @PostMapping("/message")
    @ApiOperation("Cria uma mensagem")
    public ResponseEntity postTruck(@RequestBody @Valid Message message) {
        messageRepository.save(message);
        return ResponseEntity.status(201).build();
    }


}
