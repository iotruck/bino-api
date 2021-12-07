package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Trucker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITruckerRepository extends JpaRepository<Trucker, Integer> {

    List<Trucker> findAllByCompanyIdAndIsDeletedIsFalse(Integer id);

    Integer countByCompanyIdAndIsDeletedFalse(Integer id);

    Trucker findByCpfAndIsDeletedFalse(String cpf);

    Trucker findByEmailAndPasswordAndIsDeletedFalse(String email, String password);

}
