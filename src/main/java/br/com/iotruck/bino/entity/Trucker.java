package br.com.iotruck.bino.entity;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
