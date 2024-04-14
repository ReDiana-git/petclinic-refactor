package nl.nl0e0.petclinicrefactor.unitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nl.nl0e0.petclinicrefactor.controller.appointment.AppointmentController;
import nl.nl0e0.petclinicrefactor.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicrefactor.entity.appointment.OwnerNameDTO;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.service.appointment.AppointmentService;
import nl.nl0e0.petclinicrefactor.service.systemActionAlert.SuccessActionAlert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
public class ControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AppointmentService appointmentService;
    @MockBean
    private SuccessActionAlert successActionAlert;

    @Test
    public void testCreateAppointment() throws Exception {

        Gson gson = new Gson();

        CreateAppointmentDTO testData = new CreateAppointmentDTO();
        testData.setPetId(2);
        testData.setOwnerId(1);
        testData.setVetId(1);
        testData.setAppointmentDate(LocalDateTime.now());

        // 模擬方法不拋出異常
        doNothing().when(appointmentService).checkCreateAppointmentDTOValidation(testData);
        // 驗證方法是否被調用
//        verify(appointmentService).checkCreateAppointmentDTOValidation(testData);

        when(appointmentService.createAppointment(testData)).thenReturn(new MedicalRecord());
        doNothing().when(successActionAlert).createAppointmentAlert(new MedicalRecord());

        mockMvc.perform(post("/appointment/createAppointments")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(gson.toJson(testData)))
                        .content(objectMapper.writeValueAsString(testData)))
                .andExpect(status().isCreated());

    }

    @Test
    public void testGetAppointment() throws Exception{
        List<BaseRecord> records = new ArrayList<>();
        records.add(new BaseRecord());

        OwnerNameDTO ownerNameDTO = new OwnerNameDTO();
        ownerNameDTO.setFirstName("Steven");
        ownerNameDTO.setLastName("Curry");
        when(appointmentService.getAppointmentsByOwnerName(ownerNameDTO)).thenReturn(records);

        mockMvc.perform(post("/appointment/getAppointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ownerNameDTO)))
                .andExpect(status().isOk());
    }
}
