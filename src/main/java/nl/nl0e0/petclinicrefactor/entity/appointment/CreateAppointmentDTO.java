package nl.nl0e0.petclinicrefactor.entity.appointment;

import java.time.LocalDateTime;

public class CreateAppointmentDTO {

	public Integer getOwnerId() {
		return ownerId;
	}

	public Integer getPetId() {
		return petId;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	private Integer ownerId;
	private Integer petId;
	private LocalDateTime appointmentDate;

}
