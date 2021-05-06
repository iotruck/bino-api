package br.com.iotruck.bino.services;

import br.com.iotruck.bino.dto.SecurityAnalystDto;
import br.com.iotruck.bino.entity.SecurityAnalyst;
import br.com.iotruck.bino.repository.ISecurityAnalystRepository;
import br.com.iotruck.bino.services.interfaces.ISecurityAnalystServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SecurityAnalystServices implements ISecurityAnalystServices {

    private final ISecurityAnalystRepository repository;


    public Boolean create(SecurityAnalyst analyst) {
        repository.save(analyst);
        return true;
    }

    public Boolean update(int id, SecurityAnalyst analyst) {
        if (repository.existsById(id)) {
            analyst.setId(id);
            repository.save(analyst);

            return true;
        }
        return false;
    }

    public Boolean delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<SecurityAnalystDto> getAll() {

        List<SecurityAnalyst> analyst = repository.findAll();

        return analyst.stream().map(s -> new SecurityAnalystDto(s)).collect(Collectors.toList());
    }

    public Optional<SecurityAnalyst> getById(int id) {

        return repository.findById(id);

    }

    public SecurityAnalystDto login(String email, String password) {
        return new SecurityAnalystDto(repository.findByEmailAndPassword(email, password));
    }
}
