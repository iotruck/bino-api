package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.SecurityAnalyst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISecurityAnalystRepository extends JpaRepository<SecurityAnalyst, Integer> {

    SecurityAnalyst findByEmailAndPassword(String email, String password);

}
