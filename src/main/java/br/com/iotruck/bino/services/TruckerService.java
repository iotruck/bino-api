package br.com.iotruck.bino.services;

import br.com.iotruck.bino.entity.Trucker;
import br.com.iotruck.bino.repository.ITruckerRepository;
import br.com.iotruck.bino.services.interfaces.ITruckerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TruckerService implements ITruckerService {

    private final ITruckerRepository repository;

    @Override
    public Boolean create(Trucker trucker) {
        repository.save(trucker);
        return true;
    }

    @Override
    public Boolean update(int id, Trucker trucker) {
        if (repository.existsById(id)){
            trucker.setId(id);
            repository.save(trucker);

            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(int id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }


    public List<Trucker> findAllByCompanyId(Integer id) {

        return repository.findAllByCompanyId(id);

    }

    @Override
    public Optional<Trucker> getById(int id) {

        return repository.findById(id);

    }
}
