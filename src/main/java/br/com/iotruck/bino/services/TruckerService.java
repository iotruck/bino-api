package br.com.iotruck.bino.services;

import br.com.iotruck.bino.dto.SecurityAnalystDto;
import br.com.iotruck.bino.dto.TruckerDto;
import br.com.iotruck.bino.entity.SecurityAnalyst;
import br.com.iotruck.bino.entity.Trucker;
import br.com.iotruck.bino.repository.ITruckerRepository;
import br.com.iotruck.bino.services.interfaces.ITruckerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (repository.existsById(id)) {
            trucker.setId(id);
            repository.save(trucker);

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

    public List<TruckerDto> findAllByCompanyId(Integer id) {

        List<Trucker> truckers = repository.findAllByCompanyIdAndIsDeletedIsFalse(id);


        return truckers.stream().map(t -> new TruckerDto(t)).collect(Collectors.toList());

    }

    public Integer countByCompanyId(Integer id) {

        return repository.countByCompanyIdAndIsDeletedFalse(id);

    }

    @Override
    public Optional<Trucker> getById(int id) {

        return repository.findById(id);

    }

    @Override
    public Trucker findByCpf(String cpf) {
        Trucker trucker = repository.findByCpfAndIsDeletedFalse(cpf);
        return trucker != null ? trucker : null;
    }

    public TruckerDto login(String email, String password) {
        Trucker trucker = repository.findByEmailAndPasswordAndIsDeletedFalse(email, password);
        if (trucker != null)
            return new TruckerDto(trucker);

        return null;
    }
}
