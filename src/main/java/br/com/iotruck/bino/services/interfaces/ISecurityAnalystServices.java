package br.com.iotruck.bino.services.interfaces;

import br.com.iotruck.bino.dto.SecurityAnalystDto;
import br.com.iotruck.bino.entity.SecurityAnalyst;
import java.util.List;
import java.util.Optional;

public interface ISecurityAnalystServices {

    List<SecurityAnalystDto> getAllByCompanyId(Integer id);

    Boolean create(SecurityAnalyst analyst);

    Boolean update(int id, SecurityAnalyst analyst );

    Boolean delete(int id);

    Optional<SecurityAnalyst> getById(int id);

    SecurityAnalystDto login(String email, String password);
}
