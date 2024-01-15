package nl.nl0e0.petclinicrefactor.repository;

import jakarta.transaction.Transactional;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface MedicalRecordRepository extends Repository<MedicalRecord,String> {
	void save(MedicalRecord index);

	List<MedicalRecord> findByOwnerId(Integer ownerId);

	MedicalRecord findById(String id);

    void deleteAll();

	@Modifying
	@Transactional
	@Query("UPDATE MedicalRecord medicineRecord SET medicineRecord.state = :state WHERE medicineRecord.id = :recordId")
    void updateState(@Param("state") String state, @Param("recordId") String id);
}
