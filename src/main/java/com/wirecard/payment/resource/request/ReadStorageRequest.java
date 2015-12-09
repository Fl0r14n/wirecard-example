package com.wirecard.payment.resource.request;

import com.wirecard.payment.resource.WirecardPaymentRequest;

public class ReadStorageRequest extends WirecardPaymentRequest {

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