package com.wirecard.resource.response;

import com.wirecard.resource.WirecardResponse;

import java.net.URL;

public class InitPaymentResponse extends WirecardResponse {

    URL redirectUrl;

    public URL getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(URL redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
