package br.com.iotruck.bino.entity;

import br.com.iotruck.bino.entity.enuns.TruckerStatus;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Trucker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Past
    @NotNull
    private LocalDate birthDate;
    @CPF
    private String cpf;
    @NotBlank
    @NotNull
    private String cnh;
    @Pattern(regexp = "(\\(?\\d{2}\\))?(\\d{4,5}\\-\\d{4})")
    private String phoneNumber;
    @NotBlank
    private String certification;

    private TruckerStatus status = TruckerStatus.FREE;
    @NotNull
    @JoinColumn(name = "id_company")
    @ManyToOne
    private Company company;

    public Trucker setValue(Trucker trucker) {
        Trucker newTrucker = new Trucker();
        newTrucker.setId(trucker.getId());
        newTrucker.setName(trucker.getName());
        newTrucker.setBirthDate(trucker.getBirthDate());
        newTrucker.setCpf(trucker.getCpf());
        newTrucker.setCnh(trucker.getCnh());
        newTrucker.setPhoneNumber(trucker.getPhoneNumber());
        newTrucker.setCertification(trucker.getCertification());
        newTrucker.setCompany(trucker.getCompany());
        return newTrucker;
    }
}
