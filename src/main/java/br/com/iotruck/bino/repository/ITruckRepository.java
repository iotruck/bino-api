package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.entity.Trucker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITruckRepository extends JpaRepository<Truck, Integer> {

    List<Truck> findAllByCompanyId(Integer id);

    Integer countByCompanyId(Integer id);

}
