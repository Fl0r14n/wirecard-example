package com.wirecard.payment.serializer.impl;

import com.wirecard.payment.resource.request.InitPaymentRequest;
import com.wirecard.payment.resource.response.InitPaymentResponse;
import com.wirecard.payment.serializer.WirecardPaymentSerializer;
import com.wirecard.payment.util.SerializerUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class InitPaymentSerializer implements WirecardPaymentSerializer<InitPaymentRequest, InitPaymentResponse> {

    @Autowired
    private SerializerUtil serializerUtil;
    @Value("${wirecard.clientId}")
    private String clientId;
    @Value("${wirecard.clientSecret}")
    private String clientSecret;

    @Override
    public InitPaymentResponse from(String content) throws IOException {
        Map<String, String> params = serializerUtil.parseResponse(content);
        InitPaymentResponse response = new InitPaymentResponse();
        {
            response.setRedirectUrl(new URL(params.get("redirectUrl")));
        }
        return response;
    }

    @Override
    public UrlEncodedFormEntity to(InitPaymentRequest request) throws IOException {
        List<NameValuePair> payload = new ArrayList<>();
        payload.add(new BasicNameValuePair("customerId", clientId));
        if (request.getShopId() != null) {
            payload.add(new BasicNameValuePair("shopId", request.getShopId()));
        }
        payload.add(new BasicNameValuePair("storageId", request.getStorageId()));
        payload.add(new BasicNameValuePair("paymentType", request.getPaymentType().getType()));

        payload.add(new BasicNameValuePair("consumerIpAddress", request.getConsumerIpAddress()));
        payload.add(new BasicNameValuePair("consumerUserAgent", request.getConsumerUserAgent()));

        payload.add(new BasicNameValuePair("confirmUrl", request.getConfirmUrl().toString()));
        payload.add(new BasicNameValuePair("pendingUrl", request.getPendingUrl().toString()));
        payload.add(new BasicNameValuePair("successUrl", request.getSuccessUrl().toString()));
        payload.add(new BasicNameValuePair("failureUrl", request.getFailureURl().toString()));
        payload.add(new BasicNameValuePair("cancelUrl", request.getCancelUrl().toString()));
        payload.add(new BasicNameValuePair("serviceUrl", request.getServiceURL().toString()));

        payload.add(new BasicNameValuePair("orderIdent", request.getOrderIdent()));
        payload.add(new BasicNameValuePair("orderDescription", request.getOrderDescription()));
        payload.add(new BasicNameValuePair("currency", request.getCurrency().getCurrencyCode()));
        payload.add(new BasicNameValuePair("amount", request.getAmount().toString()));
        payload.add(new BasicNameValuePair("language", request.getLanguage().getLanguage()));

        payload.add(new BasicNameValuePair("requestFingerprintOrder", serializerUtil.getRequestFingerprintOrder(payload)));
        payload.add(new BasicNameValuePair("requestFingerprint", serializerUtil.buildSHA512(payload, clientSecret)));
        return new UrlEncodedFormEntity(payload);
    }
}
