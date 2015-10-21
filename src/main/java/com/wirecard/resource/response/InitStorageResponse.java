package com.wirecard.resource.response;

import com.wirecard.resource.WirecardResponse;

import java.net.URL;

public class InitStorageResponse extends WirecardResponse {

    private URL javascriptUrl;

    public URL getJavascriptUrl() {
        return javascriptUrl;
    }

    public void setJavascriptUrl(URL javascriptUrl) {
        this.javascriptUrl = javascriptUrl;
    }
}
