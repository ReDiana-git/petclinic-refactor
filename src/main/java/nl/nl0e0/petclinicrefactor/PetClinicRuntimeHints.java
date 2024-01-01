package nl.nl0e0.petclinicrefactor;

import nl.nl0e0.petclinicrefactor.entity.model.BaseEntity;
import nl.nl0e0.petclinicrefactor.entity.model.Person;
import nl.nl0e0.petclinicrefactor.entity.vet.Vet;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;


public class PetClinicRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.resources().registerPattern("db/*"); // https://github.com/spring-projects/spring-boot/issues/32654
        hints.resources().registerPattern("messages/*");
        hints.resources().registerPattern("META-INF/resources/webjars/*");
        hints.resources().registerPattern("mysql-default-conf");
        hints.serialization().registerType(BaseEntity.class);
        hints.serialization().registerType(Person.class);
        hints.serialization().registerType(Vet.class);
    }
}
