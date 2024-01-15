package nl.nl0e0.petclinicrefactor.service;

import nl.nl0e0.petclinicrefactor.entity.appointment.SetStateDTO;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.service.appointment.AppointmentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;

    @BeforeAll
    public static void setup(){

    }

    @Test
    public void testChangeStateInit2Consultation(){
        SetStateDTO setStateDTO = new SetStateDTO("", "consultation");
        boolean result = appointmentService.checkChangeStateAvailable(setStateDTO, AppointmentState.INIT);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testChangeStateInit2Payment(){
        SetStateDTO setStateDTO = new SetStateDTO("", "payment");
        boolean result = appointmentService.checkChangeStateAvailable(setStateDTO, AppointmentState.INIT);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testChangeStateMedicine2Payment(){
        SetStateDTO setStateDTO = new SetStateDTO("", "medicine");
        boolean result = appointmentService.checkChangeStateAvailable(setStateDTO, AppointmentState.PAYMENT);
        assertThat(result).isEqualTo(true);
    }
}
