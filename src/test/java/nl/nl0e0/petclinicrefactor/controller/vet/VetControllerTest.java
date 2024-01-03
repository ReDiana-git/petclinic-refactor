package nl.nl0e0.petclinicrefactor.controller.vet;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.google.gson.Gson;
import nl.nl0e0.petclinicrefactor.PetclinicRefactorApplication;
import nl.nl0e0.petclinicrefactor.entity.vet.Vet;
import nl.nl0e0.petclinicrefactor.entity.vet.Vets;
import nl.nl0e0.petclinicrefactor.repository.vet.VetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import java.util.Arrays;
import java.util.Collections;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VetController.class)
public class VetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private VetRepository vetRepository;
    private Vet vet;
    @Test
    void validInput() throws Exception {

        mockMvc.perform(get("/vets", 42L))
                .andExpect(status().isOk());
    }
    @Test
    public void testShowResourcesVetList() throws Exception {
        Vet vet = new Vet(); // 根据需要替换为合适的构造方法或设置方法
        when(vetRepository.findAll()).thenReturn(Arrays.asList(vet));

        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.vetList[0]").exists()); // 确保 JSON 响应有预期的结构
    }

}
