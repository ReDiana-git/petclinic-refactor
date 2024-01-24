package nl.nl0e0.petclinicrefactor.controller.medicine;

import nl.nl0e0.petclinicrefactor.entity.medicine.MedicineCounterDTO;
import nl.nl0e0.petclinicrefactor.service.medicine.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class MedicineController {
    @Autowired
    MedicineService medicineService;
    @PostMapping("/appointment/medicineCounter")
    public ResponseEntity<?> medicineCounter(@RequestBody String recordId){
        try{
            MedicineCounterDTO medicineCounterDTO = medicineService.medicineCounter(recordId);
            return ResponseEntity.status(HttpStatus.OK).body(medicineCounterDTO);
        }catch (Exception exception){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());

            // 返回包含自定义错误信息和HTTP状态码的ResponseEntity
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }

    }

}
