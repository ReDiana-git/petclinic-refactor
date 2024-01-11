package nl.nl0e0.petclinicrefactor.entity.consultation;

import org.springframework.samples.petclinic.medicalRecord.MedicalRecord;
import org.springframework.samples.petclinic.medicine.MedicineEntity;
import org.springframework.samples.petclinic.model.BaseRecord;

public class CheckConsultationDTO extends BaseRecord {
	// 客戶查看病狀態
	// 會回覆客戶此病歷的金額
	private String symptom;
	private String medicines;
	public CheckConsultationDTO(ConsultationEntity consultationEntity, MedicalRecord record, MedicineEntity medicineEntity){
		super.setOwnerId(record.getOwnerId());
		super.setPetId(record.getPetId());
		super.setState(record.getState());
		this.symptom = consultationEntity.getSymptom();
		this.medicines = medicineEntity.getMedicines();
	}
}
