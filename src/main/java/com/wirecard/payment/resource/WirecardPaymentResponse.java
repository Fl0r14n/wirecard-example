package com.wirecard.payment.resource;

import java.util.Map;

public class WirecardPaymentResponse {

    private Map<String, String> errors;

    private String storageId;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
    
    @Override
    public String toString() {
        StringBuilder errbuf = new StringBuilder();
        errbuf.append("WirecardPaymentResponse{");
        errbuf.append("storageId='").append(storageId).append("\'");
        if(errors!=null) {
            errbuf.append(", errors={");
            for(Map.Entry error : errors.entrySet()) {
                errbuf.append("('").append(error.getKey()).append("','").append(error.getValue()).append("')");
            }
            errbuf.append("}");
        }
        return errbuf.append("}").toString();
    }
}
