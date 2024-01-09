package nl.nl0e0.petclinicrefactor.repository;

import nl.nl0e0.petclinicrefactor.entity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicrefactor.entity.owner.Pet;
import org.springframework.data.repository.Repository;

public interface PetRepository extends Repository<Pet,Integer> {
   Pet findById(Integer id);
}
