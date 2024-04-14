package nl.nl0e0.petclinicrefactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(PetClinicRuntimeHints.class)
//@ComponentScan(basePackages = {"nl.nl0e0.petclinicrefactor.service"})
public class PetclinicRefactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetclinicRefactorApplication.class, args);
    }

}
