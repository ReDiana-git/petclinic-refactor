package nl.nl0e0.petclinicrefactor.entity.appointment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class AppointmentEntity implements Serializable {

    @Id
    @Column(name = "id")
    private String id ;
    @Column(name = "appointmentdate")
    private LocalDateTime appointmentDate;
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    @Column(name = "createtime")
    private LocalDateTime createTime = LocalDateTime.now();
    public String getId() {
        return id;
    }
    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }
    public AppointmentEntity(MedicalRecord medicalRecord, CreateAppointmentDTO createAppointmentDTO){
        this.id = medicalRecord.getAppointmentId();
        this.appointmentDate = createAppointmentDTO.getAppointmentDate();
    }
    public AppointmentEntity(){

    }
}
