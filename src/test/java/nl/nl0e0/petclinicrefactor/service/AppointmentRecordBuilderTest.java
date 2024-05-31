package nl.nl0e0.petclinicrefactor.service;

import nl.nl0e0.petclinicrefactor.entity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.entity.owner.Owner;
import nl.nl0e0.petclinicrefactor.entity.owner.Pet;
import nl.nl0e0.petclinicrefactor.entity.vet.Vet;
import nl.nl0e0.petclinicrefactor.service.appointment.AppointmentRecordBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AppointmentRecordBuilderTest {
    @Autowired
    AppointmentRecordBuilder appointmentRecordBuilder;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    Vet vet = new Vet();
    Owner owner = new Owner();
    Pet pet = new Pet();
    AppointmentEntity appointmentEntity = new AppointmentEntity();
    MedicalRecord medicalRecord = new MedicalRecord();
    @Test
    public void testBaseRecordBuiler(){
        String sql = "INSERT INTO payment (id, price, payment_status) VALUES (?, 0, false)";
        jdbcTemplate.update(sql, medicalRecord.getPaymentId());

        vet.setFirstName("Johny");
        vet.setLastName("Walker");
        owner.setFirstName("Harry");
        owner.setLastName("Potter");
        pet.setName("Chicken");
        medicalRecord.setState(AppointmentState.PAYMENT);
        BaseRecord baseRecord = appointmentRecordBuilder.buildBaseRecord(vet, pet, owner, appointmentEntity, medicalRecord);
        assertThat(baseRecord.getPrice()).isNotNull();

        jdbcTemplate.update("DELETE FROM payment WHERE id=?", medicalRecord.getPaymentId());
    }
}
