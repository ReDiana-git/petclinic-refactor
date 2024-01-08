package nl.nl0e0.petclinicrefactor.entity.payment;

import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;

public class CheckPaymentDTO extends BaseRecord {
	private Integer price;
	public CheckPaymentDTO(PaymentEntity paymentEntity, MedicalRecord record){
		this.price = paymentEntity.getPrice();
		super.setOwnerId(record.getOwnerId());
		super.setPetId(record.getPetId());
		super.setState(record.getState());
	}
}
