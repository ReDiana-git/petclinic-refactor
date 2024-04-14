package nl.nl0e0.petclinicrefactor.unitTest;

import nl.nl0e0.petclinicrefactor.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.repository.AppointmentRepository;
import nl.nl0e0.petclinicrefactor.repository.ConsultationRepository;
import nl.nl0e0.petclinicrefactor.repository.PaymentRepository;
import nl.nl0e0.petclinicrefactor.service.appointment.AppointmentService;
import nl.nl0e0.petclinicrefactor.service.medicalRecord.MedicalRecordService;
import nl.nl0e0.petclinicrefactor.repository.MedicineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class AppointmentServiceTestUnitTest {
    @Mock
    private MedicalRecordService medicalRecordService;
    @Mock
    private ConsultationRepository consultationRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private MedicineRepository medicineRepository;
    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateAppointment(){
        CreateAppointmentDTO dto = new CreateAppointmentDTO(); // 假設你已經設置了 DTO 的屬性
        MedicalRecord medicalRecord = new MedicalRecord(); // 假設這是預期返回的對象
        when(medicalRecordService.createMedicalRecord(dto)).thenReturn(medicalRecord);

        // 執行方法
        MedicalRecord result = appointmentService.createAppointment(dto);
        // 驗證結果
        assertNotNull(result);
        assertEquals(medicalRecord, result);

    }
}
