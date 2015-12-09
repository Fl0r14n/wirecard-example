package com.wirecard.payment.resource.response;

import com.wirecard.payment.resource.WirecardPaymentResponse;

import java.net.URL;

public class InitPaymentResponse extends WirecardPaymentResponse {

    private URL redirectUrl;

    public URL getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(URL redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "InitPaymentResponse{"
                + "redirectUrl=" + redirectUrl
                + "\n" + super.toString()
                + "}";
    }
}
