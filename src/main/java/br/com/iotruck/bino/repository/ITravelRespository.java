package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITravelRespository extends JpaRepository<Travel, Integer> {

    List<Travel> findAllByAnalystId(Integer id);

    Travel findByAnalystIdAndId(Integer analystId, Integer id);
}
