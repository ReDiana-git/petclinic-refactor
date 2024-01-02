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
    public void testShowVetList() throws Exception {
        // 创建一个空的 Vet 列表
        Page<Vet> vetPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);

        // 模拟 findPaginated 方法的返回值
        when(vetRepository.findAll(any(PageRequest.class))).thenReturn(vetPage);

        // 执行 GET 请求并验证结果
        mockMvc.perform(get("/vets.html").param("page", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("vets/vetList")) // 替换为实际的视图名称
                .andExpect(model().attributeExists("listVets", "currentPage", "totalPages", "totalItems"));
    }

}
