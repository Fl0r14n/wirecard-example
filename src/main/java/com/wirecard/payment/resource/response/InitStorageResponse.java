package com.wirecard.payment.resource.response;

import com.wirecard.payment.resource.WirecardPaymentResponse;

import java.net.URL;

public class InitStorageResponse extends WirecardPaymentResponse {

    private URL javascriptUrl;

    public URL getJavascriptUrl() {
        return javascriptUrl;
    }

    public void setJavascriptUrl(URL javascriptUrl) {
        this.javascriptUrl = javascriptUrl;
    }

    @Override
    public String toString() {
        return "InitStorageResponse{"
                + "javascriptUrl=" + javascriptUrl
                + "\n" + super.toString()
                + "}";
    }
}
