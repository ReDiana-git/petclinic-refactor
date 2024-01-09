package nl.nl0e0.petclinicrefactor.entity.appointment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerNameDTO {
    @NotBlank(message = "First name must not be empty")
    String firstName;
    @NotBlank(message = "Last name must not be empty")
    String lastName;

    @Override
    public String toString(){
        return "first name is " + firstName + "\nlast name is " + lastName;
    }
}
