package com.wirecard.payment.serializer;

import com.example.WirecardSeamlessExampleApplication;
import com.wirecard.payment.resource.response.ConfirmedPaymentResponse;
import com.wirecard.payment.resource.type.PaymentStateType;
import com.wirecard.payment.serializer.impl.ConfirmedPaymentSerializer;
import java.io.IOException;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WirecardSeamlessExampleApplication.class)
@WebAppConfiguration
public class SerializerTest {

    private final String confirmPaymentResponseString = "amount=99.99&currency=EUR&paymentType=CCARD&financialInstitution=Visa&language=de&orderNumber=1460894&paymentState=SUCCESS&authenticated=No&anonymousPan=0004&expiry=04%2F2018&cardholder=asd&maskedPan=940000%2A%2A%2A%2A%2A%2A0004&gatewayReferenceNumber=DGW_1460894_RN&gatewayContractNumber=DemoContractNumber123&avsResponseCode=X&avsResponseMessage=Demo+AVS+ResultMessage&avsProviderResultCode=X&avsProviderResultMessage=Demo+AVS+ProviderResultMessage&responseFingerprintOrder=amount%2Ccurrency%2CpaymentType%2CfinancialInstitution%2Clanguage%2CorderNumber%2CpaymentState%2Cauthenticated%2CanonymousPan%2Cexpiry%2Ccardholder%2CmaskedPan%2CgatewayReferenceNumber%2CgatewayContractNumber%2CavsResponseCode%2CavsResponseMessage%2CavsProviderResultCode%2CavsProviderResultMessage%2Csecret%2CresponseFingerprintOrder&responseFingerprint=7b96cda8363f4720f765f5d84cd121b443d4b377ab86010332275d0893d7c51719742f5e9609e7029dee7679f876f6252df30220c2c1323824a4f0cdd43a8f3f&";

    @Autowired
    private ConfirmedPaymentSerializer confirmedPaymentSerializer;

    @Test
    public void confirm_payment_should_be_serialized() throws IOException {
        ConfirmedPaymentResponse response = confirmedPaymentSerializer.from(confirmPaymentResponseString);
        System.out.println(response);
        Assert.assertEquals(PaymentStateType.SUCCESS, response.getPaymentState());
    }
}
