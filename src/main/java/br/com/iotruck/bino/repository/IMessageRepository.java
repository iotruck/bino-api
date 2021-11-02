package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByFeedIdOrderByDateTimeMessageDesc(Integer feedId);
}
