package com.wirecard.resource;

public class StorageInitResponse {
    private String storageId;
    private String javascriptUrl;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getJavascriptUrl() {
        return javascriptUrl;
    }

    public void setJavascriptUrl(String javascriptUrl) {
        this.javascriptUrl = javascriptUrl;
    }
}
