package nl.nl0e0.petclinicrefactor.entity.appointment;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CreateAppointmentDTO {
	private Integer ownerId;
	private Integer petId;
	private Integer vetId;
	private LocalDateTime appointmentDate;

}
