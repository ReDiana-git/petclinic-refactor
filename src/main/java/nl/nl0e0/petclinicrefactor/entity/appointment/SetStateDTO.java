package nl.nl0e0.petclinicrefactor.entity.appointment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;

@Entity
@Getter
public class SetStateDTO {
    @Id
    String recordId;
    String state;
    public SetStateDTO(String recordId, String state){
        this.recordId = recordId;
        this.state = state;
    }
    public SetStateDTO(){

    }
}
