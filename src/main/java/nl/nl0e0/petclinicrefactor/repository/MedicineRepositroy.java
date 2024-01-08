package nl.nl0e0.petclinicrefactor.repository;

import nl.nl0e0.petclinicrefactor.entity.medicine.MedicineEntity;
import org.springframework.data.repository.Repository;

public interface MedicineRepositroy extends Repository<MedicineEntity,String> {
	void save(MedicineEntity medicineEntity);

	MedicineEntity findById(String medicineId);
}
