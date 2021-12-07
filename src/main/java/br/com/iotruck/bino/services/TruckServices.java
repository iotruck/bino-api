package br.com.iotruck.bino.services;

import br.com.iotruck.bino.dto.TruckDto;
import br.com.iotruck.bino.entity.Truck;
import br.com.iotruck.bino.repository.ITruckRepository;
import br.com.iotruck.bino.services.interfaces.ITruckServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TruckServices implements ITruckServices {

    private final ITruckRepository repository;

    @Override
    public Boolean create(Truck truck) {
        repository.save(truck);
        return true;
    }

    @Override
    public Boolean update(int id, Truck truck) {
        if (repository.existsById(id)) {
            truck.setId(id);
            repository.save(truck);

            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<TruckDto> findAllByCompanyId(Integer id) {
        List<Truck> trucks = repository.findAllByCompanyIdAndIsDeletedIsFalse(id);
        return trucks.stream().map(t -> new TruckDto(t)).collect(Collectors.toList());
    }

    @Override
    public Optional<Truck> getById(int id) {

        return repository.findById(id);

    }

    public Integer countByCompanyId(Integer id) {

        return repository.countByCompanyIdAndIsDeletedIsFalse(id);

    }
}
