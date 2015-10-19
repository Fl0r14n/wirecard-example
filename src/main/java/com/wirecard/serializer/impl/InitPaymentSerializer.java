package com.wirecard.serializer.impl;

import com.wirecard.resource.request.InitPaymentRequest;
import com.wirecard.resource.response.InitPaymentResponse;
import com.wirecard.serializer.WirecardSerializer;
import com.wirecard.util.SerializerUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class InitPaymentSerializer implements WirecardSerializer<InitPaymentRequest, InitPaymentResponse> {

    @Autowired
    SerializerUtil serializerUtil;
    @Value("${wirecard.clientId}")
    private String clientId;
    @Value("${wirecard.clientSecret}")
    private String clientSecret;

    @Override
    public InitPaymentResponse from(InputStream content) {
        Map<String, String> params = null;
        try {
            params = serializerUtil.parseResponse(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InitPaymentResponse response = new InitPaymentResponse();
        //TODO
        return response;
    }

    @Override
    public UrlEncodedFormEntity to(InitPaymentRequest request) {
        List<NameValuePair> payload = new ArrayList<>();
        payload.add(new BasicNameValuePair("customerId", clientId));
        if(request.getShopId() != null) {
            payload.add(new BasicNameValuePair("shopId", request.getShopId()));
        }
        payload.add(new BasicNameValuePair("storageId", request.getStorageId()));
        payload.add(new BasicNameValuePair("paymentType", request.getPaymentType().getType()));

        payload.add(new BasicNameValuePair("consumerIpAddress", request.getConsumerIpAddress()));
        payload.add(new BasicNameValuePair("consumerUserAgent", request.getConsumerUserAgent()));
        payload.add(new BasicNameValuePair("confirmURL", request.getConfirmUrl().toString()));

        payload.add(new BasicNameValuePair("pendingURL", request.getPendingUrl().toString()));
        payload.add(new BasicNameValuePair("successURL", request.getSuccessUrl().toString()));
        payload.add(new BasicNameValuePair("failureURL", request.getFailureURl().toString()));
        payload.add(new BasicNameValuePair("cancelURL", request.getCancelUrl().toString()));
        payload.add(new BasicNameValuePair("serviceURL", request.getServiceURL().toString()));

        payload.add(new BasicNameValuePair("orderIdent", request.getOrderIdent().toString()));
        payload.add(new BasicNameValuePair("orderDescription", request.getOrderDescription()));
        payload.add(new BasicNameValuePair("currency", request.getCurrency()));
        payload.add(new BasicNameValuePair("amount", request.getAmount()));
        payload.add(new BasicNameValuePair("language", request.getLanguage().getType()));

        payload.add(new BasicNameValuePair("requestFingerprintOrder", serializerUtil.getRequestFingerprintOrder(payload)));
        payload.add(new BasicNameValuePair("requestFingerprint", serializerUtil.buildSHA512(payload, clientSecret)));

        try {
            return new UrlEncodedFormEntity(payload);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
