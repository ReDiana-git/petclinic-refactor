package nl.nl0e0.petclinicrefactor.service.consultation;

import nl.nl0e0.petclinicrefactor.entity.consultation.CheckConsultationDTO;
import nl.nl0e0.petclinicrefactor.entity.consultation.ConsultationEntity;
import nl.nl0e0.petclinicrefactor.entity.consultation.UpdateConsultationDTO;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.medicine.MedicineEntity;
import nl.nl0e0.petclinicrefactor.repository.ConsultationRepository;
import nl.nl0e0.petclinicrefactor.service.medicalRecord.MedicalRecordService;
import nl.nl0e0.petclinicrefactor.service.medicine.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    MedicalRecordService medicalRecordService;
    @Autowired
    MedicineService medicineService;
    public void createConsultation(MedicalRecord medicalRecord){
        ConsultationEntity consultationEntity = new ConsultationEntity(medicalRecord);
        consultationRepository.save(consultationEntity);
    }




    public void deleteAll() {
        consultationRepository.deleteAll();
    }

    public CheckConsultationDTO checkConsultation(String recordId) {
        MedicalRecord record = medicalRecordService.findByRecorId(recordId);
        ConsultationEntity consultationEntity = consultationRepository.findById(record.getConsultationId());
        MedicineEntity medicineEntity = medicineService.findRecordById(record.getMedicineId());
        return new CheckConsultationDTO(consultationEntity.getSymptom(),
                medicineEntity.getMedicines(),
                record.getOwnerId(),
                record.getPetId(),
                record.getState());
    }

    public void updateConsultation(UpdateConsultationDTO updateConsultationDTO) {
        MedicalRecord medicalRecord = medicalRecordService.findByRecorId(updateConsultationDTO.getRecordId());
        medicineService.updateMedicine(medicalRecord.getMedicineId(),updateConsultationDTO.getMedicines());
        consultationRepository.updateSymptom(medicalRecord.getConsultationId(), updateConsultationDTO.getSymptom());
    }
}