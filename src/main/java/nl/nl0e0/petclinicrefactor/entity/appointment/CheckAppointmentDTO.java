package nl.nl0e0.petclinicrefactor.entity.appointment;

import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import java.time.LocalDateTime;

public class CheckAppointmentDTO extends BaseRecord {
	public String getRecordId() {
		return recordId;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	private String recordId;
	private String appointmentId;
	private LocalDateTime appointmentDate;

	public CheckAppointmentDTO(AppointmentEntity appointmentEntity, MedicalRecord record){
		this.recordId = record.getId();
		this.appointmentId = record.getAppointmentId();
		this.appointmentDate = appointmentEntity.getAppointmentDate();
		super.setPetId(record.getPetId());
		super.setOwnerId(record.getOwnerId());
		super.setState(record.getState());
	}
}
