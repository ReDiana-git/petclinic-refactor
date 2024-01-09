package nl.nl0e0.petclinicrefactor.service.consultation;

import nl.nl0e0.petclinicrefactor.entity.consultation.ConsultationEntity;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {
    @Autowired
    ConsultationRepository consultationRepository;
    public void createConsultation(MedicalRecord medicalRecord){
        ConsultationEntity consultationEntity = new ConsultationEntity(medicalRecord);
        consultationRepository.save(consultationEntity);
    }

    public void deleteAll() {
        consultationRepository.deleteAll();
    }
}
