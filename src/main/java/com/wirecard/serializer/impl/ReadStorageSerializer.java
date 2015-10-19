package com.wirecard.serializer.impl;

import com.wirecard.resource.request.ReadStorageRequest;
import com.wirecard.resource.response.ReadStorageResponse;
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
public class ReadStorageSerializer implements WirecardSerializer<ReadStorageRequest, ReadStorageResponse> {

    @Autowired
    SerializerUtil serializerUtil;
    @Value("${wirecard.clientId}")
    private String clientId;
    @Value("${wirecard.clientSecret}")
    private String clientSecret;

    @Override
    public ReadStorageResponse from(InputStream content) throws IOException {
        Map<String, String> params = null;
        try {
            params = serializerUtil.parseResponse(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReadStorageResponse response = new ReadStorageResponse();
        response.setStorageId(params.get("storageId"));
        //TODO
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
        try {
            return new UrlEncodedFormEntity(payload);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
