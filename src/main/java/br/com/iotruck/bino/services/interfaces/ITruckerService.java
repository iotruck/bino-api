package br.com.iotruck.bino.services.interfaces;

import br.com.iotruck.bino.dto.TruckerDto;
import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.entity.Trucker;
import java.util.List;
import java.util.Optional;

public interface ITruckerService {
    Boolean create(Trucker trucker);

    Boolean update(int id, Trucker trucker );

    Boolean delete(int id);

    Optional<Trucker> getById(int id);

    List<TruckerDto> findAllByCompanyId(Integer id);

    Integer countByCompanyId(Integer id);
}
