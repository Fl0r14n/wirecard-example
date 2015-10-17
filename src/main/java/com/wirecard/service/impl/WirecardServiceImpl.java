package com.wirecard.service.impl;

import com.wirecard.builders.PaymentRequestBuilder;
import com.wirecard.builders.StorageInitRequestBuilder;
import com.wirecard.builders.StorageRequestBuilder;
import com.wirecard.resource.*;
import com.wirecard.service.WirecardService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WirecardServiceImpl implements WirecardService {

    @Value("${wirecard.clientId}")
    private String clientId;

    @Value("${wirecard.clientSecret}")
    private String secret;

    @Value("${wirecard.dataStorageInitUrl}")
    private String initUrl;

    @Value("${wirecard.dataStorageReadUrl}")
    private String storageUrl;

    @Value("${wirecard.frontentUrl}")
    private String paymentUrl;

    @Override
    public StorageInitResponse initDataStorage(StorageInitRequest params) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(initUrl);
        post.setEntity(new StorageInitRequestBuilder(params).build(clientId, secret));
        try {
            HttpEntity response = httpClient.execute(post).getEntity();
            return marshallToStorageInitResponse(parseResponse(response.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    Map<String, String> parseResponse(InputStream is) throws IOException {
        Map<String, String> payload = new HashMap<>();
        String line;
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        while((line = buf.readLine()) != null) {
            if(line.startsWith("error")) {
                throw new IOException(line);
            }
            String[] params =  line.split("&");
            for(String param: params) {
                String[] kv = param.split("=");
                payload.put(kv[0], kv[1]);
            }
        }
        return payload;
    }

    public StorageInitResponse marshallToStorageInitResponse(Map<String, String> vals) {
        StorageInitResponse response = new StorageInitResponse();
        response.setStorageId(vals.get("storageId"));
        response.setJavascriptUrl(URLDecoder.decode(vals.get("javascriptUrl")));
        return response;
    }

    public StorageResponse marshallToStorageResponse(Map<String, String> vals) {
        //TODO
        return null;
    }

    @Override
    public StorageResponse readDataStorage(StorageRequest params) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(storageUrl);
        post.setEntity(new StorageRequestBuilder(params).build(clientId, secret));
        try {
            HttpEntity response = httpClient.execute(post).getEntity();
            return marshallToStorageResponse(parseResponse(response.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String initPayment(PaymentRequest params) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(paymentUrl);
        post.setEntity(new PaymentRequestBuilder(params).build(clientId, secret));
        try {
            HttpEntity response = httpClient.execute(post).getEntity();
            //TODO
            parseResponse(response.getContent());
            return null;
            //return marshall(parseResponse(response.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
