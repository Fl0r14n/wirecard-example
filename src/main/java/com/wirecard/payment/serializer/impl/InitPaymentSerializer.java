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
        if (request.getStorageId() != null) {
            payload.add(new BasicNameValuePair("storageId", request.getStorageId()));
        }
        if (request.getPaymentType() != null) {
            payload.add(new BasicNameValuePair("paymentType", request.getPaymentType().getType()));
        }

        payload.add(new BasicNameValuePair("consumerIpAddress", request.getConsumerIpAddress()));
        payload.add(new BasicNameValuePair("consumerUserAgent", request.getConsumerUserAgent()));
        if (request.getWindowName() != null) {
            payload.add(new BasicNameValuePair("windowName", request.getWindowName().toString()));
        }

        payload.add(new BasicNameValuePair("confirmUrl", request.getConfirmUrl().toString()));
        payload.add(new BasicNameValuePair("pendingUrl", request.getPendingUrl().toString()));
        payload.add(new BasicNameValuePair("successUrl", request.getSuccessUrl().toString()));
        payload.add(new BasicNameValuePair("failureUrl", request.getFailureURl().toString()));
        payload.add(new BasicNameValuePair("cancelUrl", request.getCancelUrl().toString()));
        payload.add(new BasicNameValuePair("serviceUrl", request.getServiceURL().toString()));
        if (request.getNoScriptInfoUrl() != null) {
            payload.add(new BasicNameValuePair("noScriptInfoUrl", request.getNoScriptInfoUrl().toString()));
        }

        if (request.getOrderIdent() != null) {
            payload.add(new BasicNameValuePair("orderIdent", request.getOrderIdent()));
        }
        if (request.getOrderNumber() != null) {
            payload.add(new BasicNameValuePair("orderNumber", request.getOrderNumber().toString()));
        }
        payload.add(new BasicNameValuePair("orderDescription", request.getOrderDescription()));
        payload.add(new BasicNameValuePair("orderReference", request.getOrderReference()));

        payload.add(new BasicNameValuePair("currency", request.getCurrency().getCurrencyCode()));
        payload.add(new BasicNameValuePair("amount", request.getAmount().toString()));
        payload.add(new BasicNameValuePair("language", request.getLanguage().getLanguage()));

        if (request.getCustomerStatement() != null) {
            payload.add(new BasicNameValuePair("customerStatement", request.getCustomerStatement().toString()));
        }
        if (request.getDuplicateRequestCheck() != null) {
            payload.add(new BasicNameValuePair("duplicateRequestCheck", request.getDuplicateRequestCheck().getValue()));
        }
        if (request.getTransactionIdentifier() != null) {
            payload.add(new BasicNameValuePair("transactionIdentifier", request.getTransactionIdentifier().toString()));
        }
        if (request.getFinancialInstitution() != null) {
            payload.add(new BasicNameValuePair("financialInstitution", request.getFinancialInstitution().toString()));
        }

        payload.add(new BasicNameValuePair("requestFingerprintOrder", serializerUtil.getRequestFingerprintOrder(payload)));
        payload.add(new BasicNameValuePair("requestFingerprint", serializerUtil.buildSHA512(payload, clientSecret)));
        return new UrlEncodedFormEntity(payload);
    }
}
