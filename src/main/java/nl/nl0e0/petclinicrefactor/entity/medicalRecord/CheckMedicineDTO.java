package nl.nl0e0.petclinicrefactor.entity.medicalRecord;
import nl.nl0e0.petclinicrefactor.entity.model.BaseRecord;
import nl.nl0e0.petclinicrefactor.entity.payment.PaymentEntity;

public class CheckMedicineDTO extends BaseRecord {
	private Integer price;
	public CheckMedicineDTO(PaymentEntity paymentEntity, MedicalRecord record){
		super.setOwnerId(record.getOwnerId());
		super.setPetId(record.getPetId());
		super.setState(record.getState());
		this.price = paymentEntity.getPrice();
	}
}
