package nl.nl0e0.petclinicrefactor.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import nl.nl0e0.petclinicrefactor.entity.consultation.ConsultationEntity;


public interface ConsultationRepository extends Repository<ConsultationEntity,String> {
	void save(ConsultationEntity consultationEntity);
	ConsultationEntity findById(@Param("id") String id);


	void deleteAll();
	@Modifying
	@Transactional
	@Query("UPDATE ConsultationEntity consultationentity SET consultationentity.symptom = :symptom WHERE consultationentity.id = :consultationId")
	void updateSymptom(@Param("consultationId") String  consultationId, @Param("symptom") String symptom);
}
