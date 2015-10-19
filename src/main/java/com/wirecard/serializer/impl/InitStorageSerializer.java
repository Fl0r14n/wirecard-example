package com.wirecard.serializer.impl;

import com.wirecard.resource.request.InitStorageRequest;
import com.wirecard.resource.response.InitStorageResponse;
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
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class InitStorageSerializer implements WirecardSerializer<InitStorageRequest, InitStorageResponse> {

    @Autowired
    SerializerUtil serializerUtil;
    @Value("${wirecard.clientId}")
    private String clientId;
    @Value("${wirecard.clientSecret}")
    private String clientSecret;

    @Override
    public InitStorageResponse from(InputStream content) throws IOException {
        Map<String, String> params = serializerUtil.parseResponse(content);
        InitStorageResponse response = new InitStorageResponse();
        {
            response.setStorageId(params.get("storageId"));
            response.setJavascriptUrl(new URL(URLDecoder.decode(params.get("javascriptUrl"))));
        }
        return response;
    }

    @Override
    public UrlEncodedFormEntity to(InitStorageRequest request) throws IOException {
        List<NameValuePair> payload = new ArrayList<>();
        payload.add(new BasicNameValuePair("customerId", clientId));
        if (request.getShopId() != null) {
            payload.add(new BasicNameValuePair("shopId", request.getShopId()));
        }
        payload.add(new BasicNameValuePair("orderIdent", request.getOrderIdent()));
        payload.add(new BasicNameValuePair("returnUrl", request.getReturnUrl().toString()));
        payload.add(new BasicNameValuePair("language", request.getLanguage().getType()));
        if (request.isIframe()) {
            payload.add(new BasicNameValuePair("javascriptScriptVersion", "pci3"));
        }
        payload.add(new BasicNameValuePair("requestFingerprint", serializerUtil.buildSHA512(payload, clientSecret)));
        if (request.isIframe()) {
            payload.add(new BasicNameValuePair("creditcardShowCardholderNameField", "true"));
            payload.add(new BasicNameValuePair("creditcardShowCvcField", "true"));
            if (request.getIframeCssUrl() != null) {
                payload.add(new BasicNameValuePair("iframeCssUrl", request.getIframeCssUrl().toString()));
            }
        }
        return new UrlEncodedFormEntity(payload);
    }
}
