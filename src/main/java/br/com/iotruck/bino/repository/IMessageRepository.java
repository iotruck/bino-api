package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMessageRepository extends JpaRepository<Message, Integer> {


    @Query(value = "select m from Message m where m.Travel.id = ?1 order by m.dateTimeMessage desc LIMIT ?1"
            ,nativeQuery = true)
    List<Message> findAllByTravelIdOrderByDateTimeMessageDesc(Integer travelId, Integer limit);

    Integer countByTravelId(Integer travelId);
}
