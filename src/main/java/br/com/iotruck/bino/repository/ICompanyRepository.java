package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<Company, Integer> {
}
