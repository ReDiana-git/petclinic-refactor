package nl.nl0e0.petclinicrefactor.repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import nl.nl0e0.petclinicrefactor.entity.consultation.ConsultationEntity;


public interface ConsultationRepository extends Repository<ConsultationEntity,String> {
	void save(ConsultationEntity consultationEntity);
	ConsultationEntity findById(@Param("id") String id);


	void deleteAll();
}
