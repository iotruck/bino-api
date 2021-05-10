package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Trucker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITruckerRepository extends JpaRepository<Trucker, Integer> {

    List<Trucker> findAllByCompanyId(Integer id);

}
