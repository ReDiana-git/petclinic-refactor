package nl.nl0e0.petclinicrefactor.service.payment;

import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import nl.nl0e0.petclinicrefactor.entity.payment.PaymentEntity;
import nl.nl0e0.petclinicrefactor.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository repository;
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
}
