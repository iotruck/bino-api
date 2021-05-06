package br.com.iotruck.bino.services.interfaces;

import br.com.iotruck.bino.entity.Truck;
import java.util.List;
import java.util.Optional;

public interface ITruckServices {
    Boolean create(Truck truck);

    Boolean update(int id, Truck truck );

    Boolean delete(int id);

    List<Truck> getAll();

    Optional<Truck> getById(int id);
}
