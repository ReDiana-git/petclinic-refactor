package nl.nl0e0.petclinicrefactor.entity.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import java.io.Serializable;

@Entity
@Table(name = "payment")
public class PaymentEntity implements Serializable {

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getId() {
		return id;
	}

	public boolean getPaymentStatus() {
		return paymentStatus;
	}
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "price")
	private Integer price;
	@Column(name = "payment_status")
	private boolean paymentStatus;
	public PaymentEntity(MedicalRecord medicalRecord){
		this.id = medicalRecord.getPaymentId();
		this.paymentStatus = false;
	}
	public PaymentEntity(){

	}
}
