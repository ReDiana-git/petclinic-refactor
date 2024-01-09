package nl.nl0e0.petclinicrefactor.service.appointment;

import nl.nl0e0.petclinicrefactor.entity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.entity.owner.Owner;
import nl.nl0e0.petclinicrefactor.entity.owner.Pet;
import nl.nl0e0.petclinicrefactor.entity.payment.PaymentEntity;
import nl.nl0e0.petclinicrefactor.entity.vet.Vet;
import nl.nl0e0.petclinicrefactor.service.model.ModelSerive;
import nl.nl0e0.petclinicrefactor.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class AppointmentRecordBuilder {
    @Autowired
    ModelSerive modelSerive;
    @Autowired
    PaymentService paymentService;
    public List<BaseRecord> buildRecordsFromMedicalRecords(List<MedicalRecord> medicalRecords, AppointmentService appointmentService) {
        List<BaseRecord> records = new ArrayList<>();
        for(MedicalRecord record: medicalRecords){
            records.add(createBaseRecord(record, appointmentService));
        }
        return records;
    }

    private BaseRecord createBaseRecord(MedicalRecord record, AppointmentService appointmentService) {
        Owner owner = modelSerive.findOwner(record.getOwnerId());
        AppointmentEntity appointmentEntity = appointmentService.findAppointment(record.getAppointmentId());
        Vet vet = modelSerive.findVet(record.getVetId());
        Pet pet = modelSerive.findPet(record.getPetId());
        BaseRecord baseRecord = new BaseRecord();
        baseRecord.setVetFirstName(vet.getFirstName());
        baseRecord.setVetLastName(vet.getLastName());
        baseRecord.setOwnerFirstName(owner.getFirstName());
        baseRecord.setOwnerLastName(owner.getLastName());
        baseRecord.setPetName(pet.getName());
        baseRecord.setAppointmentDate(appointmentEntity.getAppointmentDate());
        baseRecord.setState(resolveState(record.getState(), record));
        if(record.getState() == AppointmentState.PAYMENT){
           baseRecord.setPrice(paymentService.getPayment(record.getPaymentId()));
        }
        return baseRecord;
    }

    private AppointmentState resolveState(AppointmentState state, MedicalRecord record) {
        switch (state){
            case INIT:
                return AppointmentState.INIT;
            case CONSULTAION:
                return AppointmentState.CONSULTAION;
            case PAYMENT:
                return AppointmentState.PAYMENT; // Set additional properties if necessary
            case MEDICINE:
                return AppointmentState.MEDICINE;
            default:
                throw new IllegalStateException("Unknown state: " + state);
        }
    }

}
