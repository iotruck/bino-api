package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITruckRepository extends JpaRepository<Truck, Integer> {
}
