package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.dto.TravelDto;
import br.com.iotruck.bino.entity.Travel;
import br.com.iotruck.bino.entity.Trucker;
import br.com.iotruck.bino.entity.enuns.TravelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ITravelRepository extends JpaRepository<Travel, Integer> {

    @Query("select t from Travel t where t.analyst.id = ?1 and t.trucker.isDeleted = false")
    List<TravelDto> findAllByAnalystId(Integer id);

    @Query("select t from Travel t where t.trucker.id = ?1 and t.trucker.isDeleted = false")
    List<TravelDto> findAllByTruckertId(Integer id);

    Travel findByAnalystIdAndId(Integer analystId, Integer id);

    Travel findByTruckerIdAndId(Integer truckerId, Integer id);

    @Query(value = "select * from travel t where t.id_trucker = :truckerId and t.id_truck= :truckId and t.date_travel =  convert(date,:dateTravel,23)",nativeQuery = true)
    Travel findTravelByTruckerAndTruckAndDateTravel(Integer truckerId, Integer truckId, String dateTravel);

    @Query("select t from Travel t where t.codigo like %?1%")
    List<TravelDto> findByCodigoLike(String codigo);

    Travel findFirstByTruckerIdAndDateTravel(Integer id, LocalDate dateTravel);

    Travel findFirstByTruckerIdAndStatus(Integer id, TravelStatus travelStatus);

}
