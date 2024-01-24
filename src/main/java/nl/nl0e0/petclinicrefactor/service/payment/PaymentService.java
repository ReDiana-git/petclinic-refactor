package nl.nl0e0.petclinicrefactor.service.payment;

import nl.nl0e0.petclinicrefactor.entity.appointment.SetStateDTO;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.model.AppointmentState;
import nl.nl0e0.petclinicrefactor.entity.payment.*;
import nl.nl0e0.petclinicrefactor.repository.PaymentRepository;
import nl.nl0e0.petclinicrefactor.service.appointment.AppointmentService;
import nl.nl0e0.petclinicrefactor.service.medicalRecord.MedicalRecordService;
import nl.nl0e0.petclinicrefactor.service.medicine.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository repository;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    MedicalRecordService medicalRecordService;
    public void createPayment(MedicalRecord medicalRecord){
        PaymentEntity payment = new PaymentEntity(medicalRecord);
        repository.save(payment);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Integer getPayment(String paymentId) {
        return repository.findById(paymentId).getPrice();
    }
    public PaymentInfoDTO getPaymentInfo(String recordId){
        MedicalRecord medicalRecord = medicalRecordService.findByRecorId(recordId);
        PaymentEntity paymentEntity = repository.findById(medicalRecord.getPaymentId());
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO(recordId, paymentEntity.getPrice(), medicalRecord.getState2String());
        return paymentInfoDTO;
    }
    public PaymentSucessDTO payment(PaymentDTO paymentDTO){
        CardEntity cardEntity = new CardEntity(paymentDTO.getCardNumber(), paymentDTO.getCardFirstName(), paymentDTO.getCardLastName(), paymentDTO.getCheckNum());
        if(checkCreditCardWithLuhnAlgor(cardEntity))
            throw new IllegalArgumentException("The Card Number is not available!");
        appointmentService.setState(new SetStateDTO(paymentDTO.getRecordId(), "medicine"));
        MedicalRecord medicalRecord = medicalRecordService.findByRecorId(paymentDTO.getRecordId());
        return new PaymentSucessDTO(medicalRecord.getPaymentId(), medicalRecord.getState2String());
    }
    public boolean checkCreditCardWithLuhnAlgor(CardEntity cardEntity){
        String cardNum = cardEntity.getCardNumber();
        if(cardNum.length() != 16){
            System.out.println("The Card Num is " + cardNum.length() +" long.");
            return false;
        }
        int checkNumSum;
        if((cardNum.charAt(0) - '0') * 2 > 9)
            checkNumSum = (cardNum.charAt(0) - '0') * 2 % 10 + (cardNum.charAt(0) - '0') * 2 / 10;
        else
            checkNumSum = (cardNum.charAt(0) - '0') * 2;
        for(int i = 1; i < 15; i+=2){
            checkNumSum += cardNum.charAt(i) - '0';
            int tmp = (cardNum.charAt(i + 1) -'0') * 2;
            if(tmp > 9)
                checkNumSum = checkNumSum + tmp % 10 + tmp / 10;
            else
                checkNumSum += tmp;
        }
        return (cardNum.charAt(15) - '0') == (10 - checkNumSum % 10);
    }
}
