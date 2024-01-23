package nl.nl0e0.petclinicrefactor.controller.appointment;

import com.google.gson.Gson;
import nl.nl0e0.petclinicrefactor.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicrefactor.entity.appointment.OwnerNameDTO;
import nl.nl0e0.petclinicrefactor.entity.appointment.SetStateDTO;
import nl.nl0e0.petclinicrefactor.service.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;
	Gson gson = new Gson();

	@PostMapping("/appointment/createAppointments")
	public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentDTO createAppointMentDTO){
		try{
			appointmentService.checkCreateAppointmentDTOValidation(createAppointMentDTO);
			appointmentService.createAppointment(createAppointMentDTO);
		}catch (Exception exception){
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", LocalDateTime.now());
			body.put("message", exception.getMessage());

			// 返回包含自定义错误信息和HTTP状态码的ResponseEntity
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		}


		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

//	@PostMapping("/appointment/getAppointments")
	public ResponseEntity<?> getAppointment(@RequestBody String OwnerId){
		List<?> appointmentEntities = appointmentService.getAppointmentsByOwnerId(Integer.valueOf(OwnerId));
		return ResponseEntity.status(HttpStatus.OK).body(appointmentEntities);
	}
	@PostMapping("/appointment/getAppointments")
	public ResponseEntity<?> getAppointmentByOwnerName(@RequestBody OwnerNameDTO ownerNameDTO){
//		appointmentService.checkValid(ownerNameDTO);
		List<?> appointmentEntities = appointmentService.getAppointmentsByOwnerName(ownerNameDTO);
		return ResponseEntity.status(HttpStatus.OK).body(appointmentEntities);
	}

	@PostMapping("/appointment/setState")
	public ResponseEntity<?> setState(@RequestBody SetStateDTO setStateDTO){
		appointmentService.setState(setStateDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/delete")
	public ResponseEntity<?> deleteAll(){
		appointmentService.deleteAll();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
