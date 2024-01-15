package nl.nl0e0.petclinicrefactor.service.appointment;

import nl.nl0e0.petclinicrefactor.entity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicrefactor.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicrefactor.entity.appointment.OwnerNameDTO;
import nl.nl0e0.petclinicrefactor.entity.appointment.SetStateDTO;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.entity.owner.Owner;
import nl.nl0e0.petclinicrefactor.repository.*;
import nl.nl0e0.petclinicrefactor.service.consultation.ConsultationService;
import nl.nl0e0.petclinicrefactor.service.medicalRecord.MedicalRecordService;
import nl.nl0e0.petclinicrefactor.service.medicine.MedicineService;
import nl.nl0e0.petclinicrefactor.service.model.ModelSerive;
import nl.nl0e0.petclinicrefactor.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	MedicalRecordService medicalRecordService;
	@Autowired
	ConsultationService consultationService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	MedicineService medicineService;
	@Autowired
	AppointmentRecordBuilder appointmentRecordBuilder;
	@Autowired
	ModelSerive modelSerive;
	//客戶建立訂單
	public void createAppointment(CreateAppointmentDTO createAppointmentDTO){
		MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(createAppointmentDTO);
		consultationService.createConsultation(medicalRecord);
		paymentService.createPayment(medicalRecord);
		medicineService.createMedicine(medicalRecord);
		AppointmentEntity appointment = new AppointmentEntity(medicalRecord, createAppointmentDTO);
		appointmentRepository.save(appointment);
	}
	public List<?> getAppointmentsByOwnerId(Integer owner_id){
		List<MedicalRecord> MedicalRecords = medicalRecordService.findByOwnerId(owner_id);
        return getRecordsFromStates(MedicalRecords);
	}

	public List<?> getAppointmentsByOwnerName(OwnerNameDTO ownerNameDTO) {
		Owner owner = modelSerive.findOwnerByFullName(ownerNameDTO.getFirstName(), ownerNameDTO.getLastName());
		List<MedicalRecord> MedicalRecords = medicalRecordService.findByOwnerId(owner.getId());
		return getRecordsFromStates(MedicalRecords);
	}

	private List<BaseRecord> getRecordsFromStates(List<MedicalRecord> medicalRecords){
		return appointmentRecordBuilder.buildRecordsFromMedicalRecords(medicalRecords);
	}

	public void deleteAll() {
		appointmentRepository.deleteAll();
		consultationService.deleteAll();
		medicalRecordService.deleteAll();
		medicineService.deleteAll();
		paymentService.deleteAll();
	}

	public AppointmentEntity findAppointment(String appointmentId) {
		return appointmentRepository.findById(appointmentId);
	}

	public void checkValid(OwnerNameDTO ownerNameDTO) {

	}

    public void setState(SetStateDTO setStateDTO) {
		MedicalRecord medicalRecord = medicalRecordService.findByRecorId(setStateDTO.getRecordId());
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
