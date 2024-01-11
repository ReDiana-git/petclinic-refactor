package nl.nl0e0.petclinicrefactor.service.appointment;

import nl.nl0e0.petclinicrefactor.entity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.entity.owner.Owner;
import nl.nl0e0.petclinicrefactor.entity.owner.Pet;
import nl.nl0e0.petclinicrefactor.entity.vet.Vet;
import nl.nl0e0.petclinicrefactor.repository.AppointmentRepository;
import nl.nl0e0.petclinicrefactor.service.model.ModelSerive;
import nl.nl0e0.petclinicrefactor.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentRecordBuilder {
    @Autowired
    ModelSerive modelSerive;
    @Autowired
    PaymentService paymentService;
    @Autowired
    AppointmentRepository appointmentRepository;
    public List<BaseRecord> buildRecordsFromMedicalRecords(List<MedicalRecord> medicalRecords) {
        List<BaseRecord> records = new ArrayList<>();
        for(MedicalRecord record: medicalRecords){
            records.add(createBaseRecord(record));
        }
        return records;
    }

    private BaseRecord createBaseRecord(MedicalRecord record) {
        Owner owner = modelSerive.findOwner(record.getOwnerId());
        AppointmentEntity appointmentEntity = appointmentRepository.findById(record.getAppointmentId());
        Vet vet = modelSerive.findVet(record.getVetId());
        Pet pet = modelSerive.findPet(record.getPetId());
        BaseRecord baseRecord = new BaseRecord();
        baseRecord.setVetFirstName(vet.getFirstName());
        baseRecord.setVetLastName(vet.getLastName());
        baseRecord.setOwnerFirstName(owner.getFirstName());
        baseRecord.setOwnerLastName(owner.getLastName());
        baseRecord.setPetName(pet.getName());
        baseRecord.setAppointmentDate(appointmentEntity.getAppointmentDate());
        baseRecord.setState(resolveState(record.getState()));
        if(record.getState() == AppointmentState.PAYMENT){
           baseRecord.setPrice(paymentService.getPayment(record.getPaymentId()));
        }
        return baseRecord;
    }

    private AppointmentState resolveState(AppointmentState state) {
        return switch (state) {
            case INIT -> AppointmentState.INIT;
            case CONSULTAION -> AppointmentState.CONSULTAION;
            case PAYMENT -> AppointmentState.PAYMENT; // Set additional properties if necessary
            case MEDICINE -> AppointmentState.MEDICINE;
            default -> throw new IllegalStateException("Unknown state: " + state);
        };
    }

}
