package com.wirecard.serializer.impl;

import com.wirecard.resource.WirecardRequest;
import com.wirecard.resource.response.ConfirmedPaymentResponse;
import com.wirecard.resource.type.FinancialInstitutionType;
import com.wirecard.resource.type.PaymentStateType;
import com.wirecard.resource.type.PaymentType;
import com.wirecard.resource.type.YesNoType;
import com.wirecard.serializer.WirecardSerializer;
import com.wirecard.util.SerializerUtil;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfirmedPaymentSerializer implements WirecardSerializer<WirecardRequest, ConfirmedPaymentResponse> {

    @Autowired
    private SerializerUtil serializerUtil;
    @Value("${wirecard.clientId}")
    private String clientId;
    @Value("${wirecard.clientSecret}")
    private String clientSecret;
    
    @Override
    public ConfirmedPaymentResponse from(InputStream content) throws IOException {
        Map<String, String> params = serializerUtil.parseResponse(content);
        if(!serializerUtil.checkFingerprint(params, clientSecret)) {
            throw new IOException("invalid fingerprint");
        }
        ConfirmedPaymentResponse response = new ConfirmedPaymentResponse(); {
            response.setAnonymousPan(params.get("anonymousPan"));
            response.setMaskedPan(params.get("maskedPan"));
            response.setFinancialInstitution(FinancialInstitutionType.valueOfType(params.get("financialInstitution")));
            response.setBrand(response.getFinancialInstitution().toString());
            response.setCardholdername(params.get("cardholder"));
            response.setExpiry(params.get("expiry"));
            response.setPaymentType(PaymentType.valueOfType(params.get("paymentType")));
            response.setAmount(BigDecimal.valueOf(Double.parseDouble(params.get("amount"))));
            response.setCurrency(Currency.getInstance(params.get("currency")));
            response.setLanguage(Locale.forLanguageTag(params.get("language")));
            response.setOrderNumber(params.get("orderNumber"));
            response.setPaymentState(PaymentStateType.valueOf(params.get("paymentState")));
            response.setAuthenticated(YesNoType.valueOfDescription(params.get("authenticated")));
            response.setGatewayReferenceNumber(params.get("gatewayReferenceNumber"));
            response.setGatewayContractNumber(params.get("gatewayContractNumber"));
            response.setAvsResponseCode(params.get("avsResponseCode"));
            response.setAvsResponseMessage(params.get("avsResponseMessage"));
            response.setAvsProviderResultCode(params.get("avsProviderResultCode"));
            response.setAvsProviderResultMessage(params.get("avsProviderResultMessage"));
        }
        return response;
    }

    @Override
    public UrlEncodedFormEntity to(WirecardRequest request) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
