package nl.nl0e0.petclinicrefactor.service.medicine;

import nl.nl0e0.petclinicrefactor.entity.consultation.UpdateConsultationDTO;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.medicine.MedicineEntity;
import nl.nl0e0.petclinicrefactor.repository.MedicineRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {
    @Autowired
    MedicineRepositroy repositroy;
    public void createMedicine(MedicalRecord medicalRecord){
        MedicineEntity medicine = new MedicineEntity(medicalRecord);
        repositroy.save(medicine);
    }
    public void updateMedicine(String MedicineId, String medicines){
        repositroy.updateMedicines(medicines, MedicineId);
    }

    public MedicineEntity findRecordById(String recordId){
        return repositroy.findById(recordId);
    }

    public void deleteAll(){
        repositroy.deleteAll();
    }
}