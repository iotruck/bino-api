package br.com.iotruck.bino.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SecurityAnalyst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size (min = 3)
    private String name;

    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotNull
    private Boolean admin = false;

    @NotNull
    @JoinColumn(name = "id_company")
    @ManyToOne
    private Company company;

}
