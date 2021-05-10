package br.com.iotruck.bino.assistant;

import br.com.iotruck.bino.entity.Location;
import br.com.iotruck.bino.entity.enuns.Subscriptions;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.*;

public class CompanyAssistant {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @CNPJ
    private String cnpj;

    @NotNull
    private Location location;

    private Subscriptions subscriptions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Subscriptions getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Subscriptions subscriptions) {
        this.subscriptions = subscriptions;
    }
}
