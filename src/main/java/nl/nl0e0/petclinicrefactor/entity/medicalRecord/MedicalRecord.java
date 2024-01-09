package nl.nl0e0.petclinicrefactor.entity.medicalRecord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.entity.appointment.CreateAppointmentDTO;

import java.io.Serializable;
import java.util.UUID;
@Getter
@Entity
@Table(name = "medicalrecord")
public class MedicalRecord implements Serializable {

	@Id
	@Column(name = "id")
	private String id = UUID.randomUUID().toString();

	@Column(name = "owner_id")
	private Integer ownerId;

	@Column(name = "vet_id")
	private Integer vetId;
	@Column(name = "state")
	private String State;

	@Column(name = "pet_id")
	private Integer petId;
	@Column(name = "appointment_id")
	private String appointmentId = UUID.randomUUID().toString();
	@Column(name = "consultation_id")
	private String consultaionId = UUID.randomUUID().toString();
	@Column(name = "payment_id")
	private String paymentId = UUID.randomUUID().toString();
	@Column(name = "medicine_id")
	private String medicineId = UUID.randomUUID().toString();


	public AppointmentState getState(){
		switch(State){
			case "init":
				return AppointmentState.INIT;
			case "consultation":
				return AppointmentState.CONSULTAION;
			case "payment":
				return AppointmentState.PAYMENT;
			case "medication":
				return AppointmentState.MEDICINE;
		}
		return null;
	}

	public MedicalRecord(CreateAppointmentDTO dto) {
		this.ownerId = dto.getOwnerId();
		this.petId = dto.getPetId();
		this.vetId = dto.getVetId();
		this.State = "init";
	}

	public MedicalRecord(){

	}
}
