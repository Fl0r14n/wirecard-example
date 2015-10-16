package com.wirecard.builders;

import com.wirecard.resource.StorageInitRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StorageInitRequestBuilder extends WirecardAbstractResourceBuilder {

    private StorageInitRequest storageInitRequest;

    public StorageInitRequestBuilder(StorageInitRequest storageInitRequest) {
        this.storageInitRequest = storageInitRequest;
    }

    public UrlEncodedFormEntity build(String clientId, String clientSescret) {
        List<NameValuePair> payload = new ArrayList<>();
        payload.add(new BasicNameValuePair("customerId", clientId));
        if (storageInitRequest.getShopId() != null) {
            payload.add(new BasicNameValuePair("shopId", storageInitRequest.getShopId()));
        }
        payload.add(new BasicNameValuePair("orderIdent", storageInitRequest.getOrderIdent()));
        payload.add(new BasicNameValuePair("returnUrl", storageInitRequest.getReturnUrl().toString()));
        payload.add(new BasicNameValuePair("language", storageInitRequest.getLanguage().getType()));
        if(storageInitRequest.isIframe()) {
            payload.add(new BasicNameValuePair("javascriptScriptVersion", "pci3"));
        }
        payload.add(new BasicNameValuePair("requestFingerprint", super.buildSHA512(payload, clientSescret)));
        if(storageInitRequest.isIframe()) {
            payload.add(new BasicNameValuePair("creditcardShowCardholderNameField", "true"));
            payload.add(new BasicNameValuePair("creditcardShowCvcField", "true"));
            if(storageInitRequest.getIframeCssUrl() != null) {
                payload.add(new BasicNameValuePair("iframeCssUrl", storageInitRequest.getIframeCssUrl().toString()));
            }
        }
        try {
            return new UrlEncodedFormEntity(payload);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
