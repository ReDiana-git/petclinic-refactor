package nl.nl0e0.petclinicrefactor.unitTest;

import nl.nl0e0.petclinicrefactor.service.appointment.AppointmentRecordBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class testAppointmentRecordBuilder {
    private AppointmentRecordBuilder appointmentRecordBuilder;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        appointmentRecordBuilder = new AppointmentRecordBuilder();
    }

}
