package nl.nl0e0.petclinicrefactor.entity.consultation;

import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.medicine.MedicineEntity;
import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;

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
