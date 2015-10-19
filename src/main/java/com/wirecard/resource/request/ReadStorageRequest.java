package com.wirecard.resource.request;

import com.wirecard.resource.WirecardRequest;

public class ReadStorageRequest extends WirecardRequest {
    private String storageId;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
}
