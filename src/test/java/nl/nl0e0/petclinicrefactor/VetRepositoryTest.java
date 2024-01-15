package nl.nl0e0.petclinicrefactor;

import nl.nl0e0.petclinicrefactor.entity.vet.Vet;
import nl.nl0e0.petclinicrefactor.repository.VetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class VetRepositoryTest {

    @Autowired
    VetRepository repository;

    @Test
    public void testRepository(){
        Vet vet = repository.findById(1);
        assertThat(vet).isNotNull();
    }
}
