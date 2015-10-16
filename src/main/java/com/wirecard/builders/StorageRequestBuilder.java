package com.wirecard.builders;

import com.wirecard.resource.StorageRequest;
import org.apache.http.client.entity.UrlEncodedFormEntity;

public class StorageRequestBuilder extends WirecardAbstractResourceBuilder {

    public StorageRequestBuilder(StorageRequest storageRequest) {
        this.storageRequest = storageRequest;
    }
    private StorageRequest storageRequest;

    @Override
    public UrlEncodedFormEntity build(String clientId, String clientSecret) {
        //TODO
        return null;
    }
}
