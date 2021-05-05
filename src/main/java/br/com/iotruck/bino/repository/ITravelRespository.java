package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITravelRespository extends JpaRepository<Travel, Integer> {

}
