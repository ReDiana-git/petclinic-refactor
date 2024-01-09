package nl.nl0e0.petclinicrefactor.repository;

import nl.nl0e0.petclinicrefactor.entity.appointment.AppointmentEntity;
import org.springframework.data.repository.Repository;


public interface AppointmentRepository extends Repository<AppointmentEntity,String> {

	void save(AppointmentEntity appointmentEntity);

	AppointmentEntity findById(String id);

//	@Query("SELECT appointmentEntity FROM AppointmentEntity appointmentEntity WHERE appointmentEntity.id = :id")
//	@Transactional(readOnly = true)
//	AppointmentEntity findById(@Param("id") String id);
	void deleteAll();


}
