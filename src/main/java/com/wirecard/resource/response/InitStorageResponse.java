package com.wirecard.resource.response;

import com.wirecard.resource.WirecardResponse;

public class InitStorageResponse extends WirecardResponse {
    private String javascriptUrl;

    public String getJavascriptUrl() {
        return javascriptUrl;
    }

    public void setJavascriptUrl(String javascriptUrl) {
        this.javascriptUrl = javascriptUrl;
    }
}
