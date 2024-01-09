package nl.nl0e0.petclinicrefactor.repository;

import nl.nl0e0.petclinicrefactor.entity.payment.PaymentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends Repository<PaymentEntity,String> {
	void save(PaymentEntity paymentEntity);
	PaymentEntity findById(@Param("id") String id);

	@Modifying
	@Query("UPDATE PaymentEntity paymentEntity SET paymentEntity.paymentStatus = :paymentStatus WHERE paymentEntity.id = :paymentId")
	PaymentEntity updatePaymentStatus(@Param("paymentId") String paymentId, @Param("paymentStatus") boolean paymentStatus);

    void deleteAll();
}
