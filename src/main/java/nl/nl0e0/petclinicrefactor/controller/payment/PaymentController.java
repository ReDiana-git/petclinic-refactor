package nl.nl0e0.petclinicrefactor.controller.payment;

import com.google.gson.Gson;
import nl.nl0e0.petclinicrefactor.entity.payment.PaymentDTO;
import nl.nl0e0.petclinicrefactor.entity.payment.PaymentInfoDTO;
import nl.nl0e0.petclinicrefactor.entity.payment.PaymentSucessDTO;
import nl.nl0e0.petclinicrefactor.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/appointment/getPaymentInfo")
    public ResponseEntity<?> getPaymentInfo(@RequestBody String recordId){
//        System.out.println(recordId);
        PaymentInfoDTO paymentInfoDTO = paymentService.getPaymentInfo(recordId);
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(paymentInfoDTO));
    }

    @PostMapping("/appointment/payment")
    public ResponseEntity<?> payment(@RequestBody PaymentDTO paymentDTO){
        try{
            PaymentSucessDTO paymentSucessDTO = paymentService.payment(paymentDTO);
            return ResponseEntity.status(HttpStatus.OK).body(paymentSucessDTO);
        }catch (Exception exception){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }
}
