package nl.nl0e0.petclinicrefactor.service.model;

import nl.nl0e0.petclinicrefactor.entity.owner.Owner;
import nl.nl0e0.petclinicrefactor.entity.owner.Pet;
import nl.nl0e0.petclinicrefactor.entity.vet.Vet;
import nl.nl0e0.petclinicrefactor.repository.OwnerRepository;
import nl.nl0e0.petclinicrefactor.repository.PetRepository;
import nl.nl0e0.petclinicrefactor.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelSerive {
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    VetRepository vetRepository;
    @Autowired
    PetRepository petRepository;
    public Owner findOwner(Integer ownerId){
        return ownerRepository.findById(ownerId);
    }
    public Vet findVet(Integer vetId){
        return vetRepository.findById(vetId);
    }
    public Pet findPet(Integer petId){
        return petRepository.findById(petId);
    }

    public Owner findOwnerByFullName(String firstName, String lastName) {
        return ownerRepository.findByFullName(firstName, lastName);
    }
}
