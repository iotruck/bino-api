package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocationRepository extends JpaRepository<Location, Integer> {
}
