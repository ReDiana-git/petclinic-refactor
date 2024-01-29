package nl.nl0e0.petclinicrefactor.service.medicine;

import nl.nl0e0.petclinicrefactor.entity.appointment.SetStateDTO;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.medicine.MedicineCounterDTO;
import nl.nl0e0.petclinicrefactor.entity.medicine.MedicineEntity;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.repository.MedicineRepositroy;
import nl.nl0e0.petclinicrefactor.service.medicalRecord.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {
    @Autowired
    MedicineRepositroy repositroy;
    @Autowired
    MedicalRecordService medicalRecordService;

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

    public MedicineCounterDTO medicineCounter(String recordId) throws IllegalAccessException {
        MedicalRecord medicalRecord = medicalRecordService.findByRecordId(recordId);
        if(!(medicalRecord.getState2String().equals("medicine")))
            throw new IllegalAccessException("You are not at medicine state.");

        setState(new SetStateDTO(recordId,"done"));
        return new MedicineCounterDTO(recordId, "done");
    }
    public void setState(SetStateDTO setStateDTO) {
        MedicalRecord medicalRecord = medicalRecordService.findByRecordId(setStateDTO.getRecordId());
        if(checkChangeStateAvailable(setStateDTO ,medicalRecord.getState())){
            medicalRecord.setState(setStateDTO.getState());
            medicalRecordService.updateState(medicalRecord);
        }
        else
            throw new RuntimeException("set State denied.");

    }
    public boolean checkChangeStateAvailable(SetStateDTO setStateDTO, AppointmentState currentState){
        switch (setStateDTO.getState()){
            case "consultation" :
                return currentState.equals(AppointmentState.INIT);
            case "payment":
                return currentState.equals(AppointmentState.CONSULTAION);
            case "medicine":
                return currentState.equals(AppointmentState.PAYMENT);
            default:
                return false;
        }
    }
}
