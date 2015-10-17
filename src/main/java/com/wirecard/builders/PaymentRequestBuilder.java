package com.wirecard.builders;

import com.wirecard.resource.PaymentRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRequestBuilder  extends WirecardAbstractResourceBuilder {

    public PaymentRequestBuilder(PaymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }
    private PaymentRequest paymentRequest;

    @Override
    public UrlEncodedFormEntity build(String clientId, String clientSecret) {
        List<NameValuePair> payload = new ArrayList<>();
        payload.add(new BasicNameValuePair("customerId", clientId));
        payload.add(new BasicNameValuePair("shopId", paymentRequest.getShopId()));
        payload.add(new BasicNameValuePair("storageId", paymentRequest.getStorageId()));
        payload.add(new BasicNameValuePair("paymentType", paymentRequest.getPaymentType().getType()));

        payload.add(new BasicNameValuePair("consumerIpAddress", paymentRequest.getConsumerIpAddress()));
        payload.add(new BasicNameValuePair("consumerUserAgent", paymentRequest.getConsumerUserAgent()));
        payload.add(new BasicNameValuePair("confirmURL", paymentRequest.getConfirmUrl().toString()));

        payload.add(new BasicNameValuePair("pendingURL", paymentRequest.getPendingUrl().toString()));
        payload.add(new BasicNameValuePair("successURL", paymentRequest.getSuccessUrl().toString()));
        payload.add(new BasicNameValuePair("failureURL", paymentRequest.getFailureURl().toString()));
        payload.add(new BasicNameValuePair("cancelURL", paymentRequest.getCancelUrl().toString()));
        payload.add(new BasicNameValuePair("serviceURL", paymentRequest.getServiceURL().toString()));

        payload.add(new BasicNameValuePair("orderIdent", paymentRequest.getOrderIdent().toString()));
        payload.add(new BasicNameValuePair("orderDescription", paymentRequest.getOrderDescription()));
        payload.add(new BasicNameValuePair("currency", paymentRequest.getCurrency()));
        payload.add(new BasicNameValuePair("amount", paymentRequest.getAmount()));
        payload.add(new BasicNameValuePair("language", paymentRequest.getLanguage().getType()));

        payload.add(new BasicNameValuePair("requestFingerprintOrder", super.getRequestFingerprintOrder(payload)));
        payload.add(new BasicNameValuePair("requestFingerprint", super.buildSHA512(payload, clientSecret)));

        try {
            return new UrlEncodedFormEntity(payload);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
