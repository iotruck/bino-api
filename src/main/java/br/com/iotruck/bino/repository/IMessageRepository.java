package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByTravelIdOrderByDateTimeMessage(Integer travelId);

    List<Message> findAllByTravelIdOrderByDateTimeMessageDesc(Integer travelId, Pageable pageable);

    Integer countByTravelId(Integer travelId);

}
