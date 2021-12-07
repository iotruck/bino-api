package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITruckRepository extends JpaRepository<Truck, Integer> {

    List<Truck> findAllByCompanyIdAndIsDeletedIsFalse(Integer id);

    Integer countByCompanyIdAndIsDeletedIsFalse(Integer id);

    Truck findByLicensePlateAndCompanyIdAndIsDeletedIsFalse(String licensePlate,Integer CompanyId);

}
