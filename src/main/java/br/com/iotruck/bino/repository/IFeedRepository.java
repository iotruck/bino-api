package br.com.iotruck.bino.repository;

import br.com.iotruck.bino.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedRepository extends JpaRepository<Feed, Integer> {

}