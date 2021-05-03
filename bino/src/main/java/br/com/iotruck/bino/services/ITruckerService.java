package br.com.iotruck.bino.services;

import br.com.iotruck.bino.entity.Trucker;
import java.util.List;
import java.util.Optional;

public interface ITruckerService {
    Boolean create(Trucker trucker);

    Boolean update(int id, Trucker trucker );

    Boolean delete(int id);

    List<Trucker> getAll();

    Optional<Trucker> getById(int id);
}
