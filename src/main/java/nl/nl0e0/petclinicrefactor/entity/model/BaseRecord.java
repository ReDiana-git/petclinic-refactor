package nl.nl0e0.petclinicrefactor.entity.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseRecord {
	private AppointmentState state;
	private String ownerFirstName;
	private String ownerLastName;
	private String vetFirstName;
	private String vetLastName;
	private String petName;
	private LocalDateTime appointmentDate;
	private Integer price;
}
