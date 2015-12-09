package com.wirecard.payment.serializer.impl;

import com.wirecard.payment.resource.WirecardPaymentRequest;
import com.wirecard.payment.resource.response.ConfirmedPaymentResponse;
import com.wirecard.payment.resource.type.FinancialInstitutionType;
import com.wirecard.payment.resource.type.PaymentStateType;
import com.wirecard.payment.resource.type.PaymentType;
import com.wirecard.payment.resource.type.YesNoType;
import com.wirecard.payment.serializer.WirecardPaymentSerializer;
import com.wirecard.payment.util.SerializerUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfirmedPaymentSerializer implements WirecardPaymentSerializer<WirecardPaymentRequest, ConfirmedPaymentResponse> {

    @Autowired
    private SerializerUtil serializerUtil;
    @Value("${wirecard.clientSecret}")
    private String clientSecret;
    
    @Override
    public ConfirmedPaymentResponse from(String content) throws IOException {
        Map<String, String> params = serializerUtil.parseResponse(content);
        ConfirmedPaymentResponse response = new ConfirmedPaymentResponse();
        if (params.containsKey("errors")) {
            response.setErrors(params);
            return response;
        }
        if (!serializerUtil.checkFingerprint(params, clientSecret)) {
            throw new IOException("invalid fingerprint");
        }

        response.setAnonymousPan(params.get("anonymousPan"));
        response.setMaskedPan(params.get("maskedPan"));
        response.setFinancialInstitution(FinancialInstitutionType.valueOfType(params.get("financialInstitution")));
        if (response.getFinancialInstitution() != null) {
            response.setBrand(response.getFinancialInstitution().toString());
        }
        response.setCardholdername(params.get("cardholder"));
        response.setExpiry(params.get("expiry"));
        response.setPaymentType(PaymentType.valueOfType(params.get("paymentType")));
        response.setAmount(BigDecimal.valueOf(Double.parseDouble(params.get("amount"))));
        response.setCurrency(Currency.getInstance(params.get("currency")));
        response.setLanguage(Locale.forLanguageTag(params.get("language")));
        response.setOrderNumber(Long.decode(params.get("orderNumber")));
        response.setPaymentState(PaymentStateType.valueOf(params.get("paymentState")));
        response.setAuthenticated(YesNoType.valueOfDescription(params.get("authenticated")));
        response.setGatewayReferenceNumber(params.get("gatewayReferenceNumber"));
        response.setGatewayContractNumber(params.get("gatewayContractNumber"));
        response.setAvsResponseCode(params.get("avsResponseCode"));
        response.setAvsResponseMessage(params.get("avsResponseMessage"));
        response.setAvsProviderResultCode(params.get("avsProviderResultCode"));
        response.setAvsProviderResultMessage(params.get("avsProviderResultMessage"));
        response.setStorageId(params.get("storageId"));        
        return response;
    }

    @Override
    public UrlEncodedFormEntity to(WirecardPaymentRequest request) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
