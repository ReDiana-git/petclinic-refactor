package nl.nl0e0.petclinicrefactor.controller.consultation;

import com.google.gson.Gson;
import nl.nl0e0.petclinicrefactor.entity.consultation.CheckConsultationDTO;
import nl.nl0e0.petclinicrefactor.entity.consultation.UpdateConsultationDTO;
import nl.nl0e0.petclinicrefactor.service.consultation.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ConsultationController {
	@Autowired
	ConsultationService consultationService;

	//給醫生查看病歷
	@PostMapping("/appointment/checkConsultation")
	public ResponseEntity<?> checkConsultation(@RequestBody String recordId){
		CheckConsultationDTO checkConsultationDTO = consultationService.checkConsultation(recordId);
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(checkConsultationDTO));
	}

	//給醫生更新病歷
	@PostMapping("/appoint/updateConsultation")
	public ResponseEntity<?> updateConsultation(@RequestBody UpdateConsultationDTO updateConsultationDTO){
		System.out.println("updateConsultationDTO Object in Controller\n" + updateConsultationDTO);
		consultationService.updateConsultation(updateConsultationDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
