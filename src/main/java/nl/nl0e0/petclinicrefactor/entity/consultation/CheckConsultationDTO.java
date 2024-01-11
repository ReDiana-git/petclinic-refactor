package nl.nl0e0.petclinicrefactor.entity.consultation;

import lombok.Getter;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import org.springframework.samples.petclinic.medicalRecord.MedicalRecord;
import org.springframework.samples.petclinic.medicine.MedicineEntity;
import org.springframework.samples.petclinic.model.BaseRecord;

@Getter
public class CheckConsultationDTO{
	// 客戶查看病狀態
	// 會回覆客戶此病歷的金額
	private String symptom;
	private String medicines;
	private Integer ownerId;
	private Integer petId;
	private AppointmentState state;

	public CheckConsultationDTO(String symptom, String medicines, Integer ownerId, Integer petId, AppointmentState state){
		this.symptom = symptom;
		this.medicines = medicines;
		this.ownerId = ownerId;
		this.petId = petId;
		this.state = state;
	}
}
