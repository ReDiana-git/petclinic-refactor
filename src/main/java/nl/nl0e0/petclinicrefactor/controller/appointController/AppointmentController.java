package nl.nl0e0.petclinicrefactor.controller.appointController;

import nl.nl0e0.petclinicrefactor.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicrefactor.entity.appointment.OwnerNameDTO;
import nl.nl0e0.petclinicrefactor.service.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@CrossOrigin
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@PostMapping("/appointment/createAppointments")
	public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentDTO createAppointMentDTO){
		appointmentService.createAppointment(createAppointMentDTO);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

//	@PostMapping("/appointment/getAppointments")
	public ResponseEntity<?> getAppointment(@RequestBody String OwnerId){
		List<?> appointmentEntities = appointmentService.getAppointmentsByOwnerId(Integer.valueOf(OwnerId));
		return ResponseEntity.status(HttpStatus.OK).body(appointmentEntities);
	}
	@PostMapping("/appointment/getAppointments")
	public ResponseEntity<?> getAppointmentByOwnerName(@RequestBody OwnerNameDTO ownerNameDTO){
		appointmentService.checkValid(ownerNameDTO);
		List<?> appointmentEntities = appointmentService.getAppointmentsByOwnerName(ownerNameDTO);
		return ResponseEntity.status(HttpStatus.OK).body(appointmentEntities);
	}

	@PostMapping("/delete")
	public ResponseEntity<?> deleteAll(){
		appointmentService.deleteAll();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
