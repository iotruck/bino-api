package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.SecurityAnalyst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISecurityAnalystRepository extends JpaRepository<SecurityAnalyst, Integer> {

    SecurityAnalyst findByEmailAndPassword(String email, String password);

    List<SecurityAnalyst> findAllByCompanyId(Integer id);

}
