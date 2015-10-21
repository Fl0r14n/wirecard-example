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

    @Override
    public String toString() {
        return "ReadStorageRequest{" +
                "storageId='" + storageId + '\'' +
                "\n" + super.toString() +
                "}";
    }
}
