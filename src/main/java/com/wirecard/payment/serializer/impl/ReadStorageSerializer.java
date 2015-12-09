package com.wirecard.payment.serializer.impl;

import com.wirecard.payment.resource.request.ReadStorageRequest;
import com.wirecard.payment.resource.response.ReadStorageResponse;
import com.wirecard.payment.resource.type.FinancialInstitutionType;
import com.wirecard.payment.resource.type.PaymentType;
import com.wirecard.payment.serializer.WirecardPaymentSerializer;
import com.wirecard.payment.util.SerializerUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ReadStorageSerializer implements WirecardPaymentSerializer<ReadStorageRequest, ReadStorageResponse> {

    @Autowired
    private SerializerUtil serializerUtil;
    @Value("${wirecard.clientId}")
    private String clientId;
    @Value("${wirecard.clientSecret}")
    private String clientSecret;

    @Override
    public ReadStorageResponse from(String content) throws IOException {
        Map<String, String> params = serializerUtil.parseResponse(content);
        ReadStorageResponse response = new ReadStorageResponse();
        {
            response.setStorageId(params.get("storageId"));
            if (Integer.parseInt(params.get("paymentInformations")) > 0) {
                response.setAnonymousPan(params.get("paymentInformation.1.anonymousPan"));
                response.setMaskedPan(params.get("paymentInformation.1.maskedPan"));
                response.setFinancialInstitution(FinancialInstitutionType.valueOfType(params.get("paymentInformation.1.financialInstitution")));
                response.setBrand(params.get("paymentInformation.1.brand"));
                response.setCardholdername(params.get("paymentInformation.1.cardholdername"));
                response.setExpiry(params.get("paymentInformation.1.expiry"));
                response.setPaymentType(PaymentType.valueOfType(params.get("paymentInformation.1.paymentType")));
            }
        }
        return response;
    }

    @Override
    public UrlEncodedFormEntity to(ReadStorageRequest request) throws IOException {
        List<NameValuePair> payload = new ArrayList<>();
        payload.add(new BasicNameValuePair("customerId", clientId));
        if (request.getShopId() != null) {
            payload.add(new BasicNameValuePair("shopId", request.getShopId()));
        }
        payload.add(new BasicNameValuePair("storageId", request.getStorageId()));
        payload.add(new BasicNameValuePair("requestFingerprint", serializerUtil.buildSHA512(payload, clientSecret)));
        return new UrlEncodedFormEntity(payload);
    }
}
