package nl.nl0e0.petclinicrefactor.service.systemActionAlert;

import nl.nl0e0.petclinicrefactor.entity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;

public class SuccessActionAlert {
    public void createAppointmentAlert(MedicalRecord medicalRecord){
        System.out.println("-----------------");
        System.out.println("Create Appointment Success.");
        System.out.println(medicalRecord);
        System.out.println("-----------------");
    }
}
