package nl.nl0e0.petclinicrefactor.service;

import nl.nl0e0.petclinicrefactor.entity.payment.CardEntity;
import nl.nl0e0.petclinicrefactor.service.payment.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    PaymentService paymentService;

    @Test
    public void testcheckCreditCardWithLuhnAlgor1(){
        String Card = "5412345678901232";
        boolean result = paymentService.checkCreditCardWithLuhnAlgor(new CardEntity(Card));
        assertThat(result).isEqualTo(true);
    }
    @Test
    public void testcheckCreditCardWithLuhnAlgor2(){
        String Card = "4496197413066425";
        boolean result = paymentService.checkCreditCardWithLuhnAlgor(new CardEntity(Card));
        assertThat(result).isEqualTo(false);
    }

}
