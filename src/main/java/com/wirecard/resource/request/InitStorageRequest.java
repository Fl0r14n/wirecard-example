package com.wirecard.resource.request;

import com.wirecard.resource.WirecardRequest;

import java.net.URL;
import java.util.Locale;

public class InitStorageRequest extends WirecardRequest {
    private String orderIdent;
    private URL returnUrl;
    private Locale language;
    private URL iframeCssUrl;
    private boolean iframe;

    public String getOrderIdent() {
        return orderIdent;
    }

    public void setOrderIdent(String orderIdent) {
        this.orderIdent = orderIdent;
    }

    public URL getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(URL returnUrl) {
        this.returnUrl = returnUrl;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public URL getIframeCssUrl() {
        return iframeCssUrl;
    }

    public void setIframeCssUrl(URL iframeCssUrl) {
        this.iframeCssUrl = iframeCssUrl;
    }

    public boolean isIframe() {
        return iframe;
    }

    public void setIframe(boolean iframe) {
        this.iframe = iframe;
    }
}
