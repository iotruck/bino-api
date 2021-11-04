package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.dto.TravelDto;
import br.com.iotruck.bino.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITravelRepository extends JpaRepository<Travel, Integer> {

    @Query("select t from Travel t where t.analyst.id = ?1")
    List<TravelDto> findAllByAnalystId(Integer id);

    Travel findByAnalystIdAndId(Integer analystId, Integer id);

    @Query("select t from Travel t where t.codigo like %?1%")
    List<TravelDto> findByCodigoLike(String codigo);

}
