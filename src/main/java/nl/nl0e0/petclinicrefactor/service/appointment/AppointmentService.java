package nl.nl0e0.petclinicrefactor.service.appointment;

import nl.nl0e0.petclinicrefactor.entity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicrefactor.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicrefactor.entity.appointment.OwnerNameDTO;
import nl.nl0e0.petclinicrefactor.entity.appointment.SetStateDTO;
import nl.nl0e0.petclinicrefactor.entity.consultation.ConsultationEntity;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.medicine.MedicineEntity;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.entity.owner.Owner;
import nl.nl0e0.petclinicrefactor.entity.payment.PaymentEntity;
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
	MedicalRecordRepository medicalRecordRepository;
	@Autowired
	ConsultationRepository consultationRepository;
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	MedicineRepository medicineRepositroy;
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
	public MedicalRecord createAppointment(CreateAppointmentDTO createAppointmentDTO){
		MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(createAppointmentDTO);
		consultationRepository.save(new ConsultationEntity(medicalRecord));
		paymentRepository.save(new PaymentEntity(medicalRecord));
		medicineRepositroy.save(new MedicineEntity(medicalRecord));
		appointmentRepository.save(new AppointmentEntity(medicalRecord, createAppointmentDTO));
		return medicalRecord;
	}

	public void checkCreateAppointmentDTOValidation(CreateAppointmentDTO createAppointMentDTO){
		String notBeNull = " should not be null.";
		if(createAppointMentDTO.getAppointmentDate() == null)
			throw new NullPointerException("Appointment Date" + notBeNull);
		if(createAppointMentDTO.getPetId() == null)
			throw new NullPointerException("Pet ID" + notBeNull);
		if(createAppointMentDTO.getOwnerId() == null)
			throw new NullPointerException("Owner ID" + notBeNull);
		if(createAppointMentDTO.getVetId() == null)
			throw new NullPointerException("Vet ID" + notBeNull);
	}
	public List<?> getAppointmentsByOwnerId(Integer owner_id){
		List<MedicalRecord> MedicalRecords = medicalRecordService.findByOwnerId(owner_id);
        return getRecordsFromStates(MedicalRecords);
	}

	public List<BaseRecord> getAppointmentsByOwnerName(OwnerNameDTO ownerNameDTO) {
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
