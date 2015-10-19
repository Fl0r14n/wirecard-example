package com.wirecard.service.impl;

import com.wirecard.resource.request.InitPaymentRequest;
import com.wirecard.resource.request.InitStorageRequest;
import com.wirecard.resource.request.ReadStorageRequest;
import com.wirecard.resource.response.InitPaymentResponse;
import com.wirecard.resource.response.InitStorageResponse;
import com.wirecard.resource.response.ReadStorageResponse;
import com.wirecard.serializer.impl.InitPaymentSerializer;
import com.wirecard.serializer.impl.InitStorageSerializer;
import com.wirecard.serializer.impl.ReadStorageSerializer;
import com.wirecard.service.WirecardService;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WirecardServiceImpl implements WirecardService {

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


    @Override
    public InitStorageResponse initDataStorage(InitStorageRequest params) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(initUrl);
        post.setEntity(initStorageSerializer.to(params));
        HttpEntity response = httpClient.execute(post).getEntity();
        return initStorageSerializer.from(response.getContent());
    }

    @Override
    public ReadStorageResponse readDataStorage(ReadStorageRequest params) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(storageUrl);
        post.setEntity(readStorageSerializer.to(params));
        HttpEntity response = httpClient.execute(post).getEntity();
        return readStorageSerializer.from(response.getContent());
    }

    @Override
    public InitPaymentResponse initPayment(InitPaymentRequest params) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(paymentUrl);
        post.setEntity(initPaymentSerializer.to(params));
        HttpEntity response = httpClient.execute(post).getEntity();
        return initPaymentSerializer.from(response.getContent());
    }
}
