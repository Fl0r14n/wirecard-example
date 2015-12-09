package com.wirecard.payment.service.impl;

import com.wirecard.payment.resource.request.InitPaymentRequest;
import com.wirecard.payment.resource.request.InitStorageRequest;
import com.wirecard.payment.resource.request.ReadStorageRequest;
import com.wirecard.payment.resource.response.ConfirmedPaymentResponse;
import com.wirecard.payment.resource.response.InitPaymentResponse;
import com.wirecard.payment.resource.response.InitStorageResponse;
import com.wirecard.payment.resource.response.ReadStorageResponse;
import com.wirecard.payment.serializer.impl.ConfirmedPaymentSerializer;
import com.wirecard.payment.serializer.impl.InitPaymentSerializer;
import com.wirecard.payment.serializer.impl.InitStorageSerializer;
import com.wirecard.payment.serializer.impl.ReadStorageSerializer;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import com.wirecard.payment.service.WirecardPaymentService;
import org.apache.http.util.EntityUtils;

@Service
public class WirecardServiceImpl implements WirecardPaymentService {

    @Value("${wirecard.dataStorageInitUrl}")
    private String initUrl;

    @Value("${wirecard.dataStorageReadUrl}")
    private String storageUrl;

    @Value("${wirecard.frontentUrl}")
    private String paymentUrl;

    @Autowired
    private InitPaymentSerializer initPaymentSerializer;

    @Autowired
    private InitStorageSerializer initStorageSerializer;

    @Autowired
    private ReadStorageSerializer readStorageSerializer;

    @Autowired
    private ConfirmedPaymentSerializer confirmedPaymentSerializer;

    @Override
    public InitStorageResponse initDataStorage(InitStorageRequest params) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(initUrl);
        post.setEntity(initStorageSerializer.to(params));
        HttpEntity response = httpClient.execute(post).getEntity();
        return initStorageSerializer.from(EntityUtils.toString(response));
    }

    @Override
    public ReadStorageResponse readDataStorage(ReadStorageRequest params) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(storageUrl);
        post.setEntity(readStorageSerializer.to(params));
        HttpEntity response = httpClient.execute(post).getEntity();
        return readStorageSerializer.from(EntityUtils.toString(response));
    }

    @Override
    public InitPaymentResponse initPayment(InitPaymentRequest params) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(paymentUrl);
        post.setEntity(initPaymentSerializer.to(params));
        HttpEntity response = httpClient.execute(post).getEntity();
        return initPaymentSerializer.from(EntityUtils.toString(response));
    }

    @Override
    public ConfirmedPaymentResponse readPaymentConfirmation(String request) throws IOException {
        return confirmedPaymentSerializer.from(request);
    }
}
