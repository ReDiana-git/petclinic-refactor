package nl.nl0e0.petclinicrefactor.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.nl0e0.petclinicrefactor.controller.appointment.AppointmentController;
import nl.nl0e0.petclinicrefactor.entity.appointment.*;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.repository.OwnerRepository;
import nl.nl0e0.petclinicrefactor.repository.PetRepository;
import nl.nl0e0.petclinicrefactor.repository.VetRepository;
import nl.nl0e0.petclinicrefactor.service.appointment.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import nl.nl0e0.petclinicrefactor.controller.appointment.AppointmentController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired AppointmentController appointmentController;


    @Test
    public void testCreateAppointment() throws Exception {
        // Create your test data, e.g., a CreateAppointmentDTO
        CreateAppointmentDTO testData = new CreateAppointmentDTO(/* fill in your test data */);
        testData.setPetId(2);
        testData.setOwnerId(1);
        testData.setVetId(1);
        testData.setAppointmentDate(LocalDateTime.now());

        // Convert the test data to JSON
        String jsonTestData = objectMapper.writeValueAsString(testData);

        // Perform the request and assert the response
        ResultActions result = mockMvc.perform(post("/appointment/createAppointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTestData))
                .andExpect(status().isCreated()); // Assert the HTTP status code
//                .andExpect(jsonPath("$.yourJsonField").value("expectedValue")); // Assert the response content

        // You can add more assertions as needed based on your application logic
    }
    @Test
    public void testCreateAppointment1() throws Exception {
        // Create your test data, e.g., a CreateAppointmentDTO
        CreateAppointmentDTO testData = new CreateAppointmentDTO(/* fill in your test data */);
        testData.setPetId(1);
        testData.setOwnerId(1);
        testData.setVetId(5);
        testData.setAppointmentDate(LocalDateTime.now());

        // Convert the test data to JSON
        String jsonTestData = objectMapper.writeValueAsString(testData);


        // Perform the request and assert the response
        ResultActions result = mockMvc.perform(post("/appointment/createAppointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTestData))
                .andExpect(status().isCreated()); // Assert the HTTP status code
//                .andExpect(jsonPath("$.yourJsonField").value("expectedValue")); // Assert the response content

        // You can add more assertions as needed based on your application logic
    }

    @Test
    public void testGetAppointmentByOwnerName() throws Exception {
        OwnerNameDTO testData = new OwnerNameDTO();
        testData.setLastName("Ohtani");
        testData.setFirstName("Shohei");

        String jsonTestData = objectMapper.writeValueAsString(testData);
        try {
            jdbcTemplate.update("INSERT INTO owners VALUES (11, 'Shohei', 'Ohtani', '1450 Oak Blvd.', 'Monona', '0912345678');");
        }catch (Exception e){
            System.out.println(e);
        }

        ResultActions result = mockMvc.perform(post("/appointment/getAppointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTestData))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
        try {
            jdbcTemplate.update("DELETE FROM owners WHERE id=11;");

        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void testGetAppointmentByOwnerName1() throws Exception {
        OwnerNameDTO testData = new OwnerNameDTO();
        testData.setLastName("Ohtani");
        testData.setFirstName("Shohei");

        CreateAppointmentDTO createAppointmentDTO = new CreateAppointmentDTO();
        createAppointmentDTO.setAppointmentDate(LocalDateTime.now());
        createAppointmentDTO.setOwnerId(11);
        createAppointmentDTO.setPetId(1);
        createAppointmentDTO.setVetId(1);

        BaseRecord baseRecord = new BaseRecord();
        baseRecord.setOwnerFirstName("Shohei");
        baseRecord.setOwnerLastName("Ohtani");
        baseRecord.setState(AppointmentState.INIT);
        baseRecord.setPetName("Leo");
        baseRecord.setVetFirstName("James");
        baseRecord.setVetLastName("Carter");
//        baseRecord.setAppointmentDate(LocalDateTime.now());
        List<BaseRecord> baseRecords = new ArrayList<>();
        baseRecords.add(baseRecord);
        String jsonTestData = objectMapper.writeValueAsString(testData);

        String baseRecordData = objectMapper.writeValueAsString(baseRecords);
        try {
            jdbcTemplate.update("INSERT INTO owners VALUES (11, 'Shohei', 'Ohtani', '1450 Oak Blvd.', 'Monona', '0912345678');");
            appointmentController.createAppointment(createAppointmentDTO);
        }catch (Exception e){
            System.out.println(e);
        }

        ResultActions result = mockMvc.perform(post("/appointment/getAppointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTestData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].petName").value("Leo"));
        try {
            jdbcTemplate.update("DELETE FROM owners WHERE id=11;");
            appointmentController.deleteAll();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void testSetState() throws Exception{
        jdbcTemplate.update("INSERT INTO medicalrecord(id, owner_id, pet_id, state) VALUES ('123', 1, 1, 'init')");
        SetStateDTO setStateDTO = new SetStateDTO("123", "consultation");
        String jsonTestData = objectMapper.writeValueAsString(setStateDTO);


        ResultActions result = mockMvc.perform(post("/appointment/setState")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTestData))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception{
        ResultActions result = mockMvc.perform(post("/delete"))
                .andExpect(status().isOk());
    }
}
