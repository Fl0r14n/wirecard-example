package com.wirecard.resource;

public class WirecardResponse {

    private String storageId;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    @Override
    public String toString() {
        return "WirecardResponse{" +
                "storageId='" + storageId + '\'' +
                '}';
    }
}
