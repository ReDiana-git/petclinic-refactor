package nl.nl0e0.petclinicrefactor.repository;

import org.springframework.data.repository.Repository;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import java.util.List;

@org.springframework.stereotype.Repository
public interface MedicalRecordRepository extends Repository<MedicalRecord,String> {
	void save(MedicalRecord index);

	List<MedicalRecord> findByOwnerId(Integer ownerId);

	MedicalRecord findById(String recordId);
}
