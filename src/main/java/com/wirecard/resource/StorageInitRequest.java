package com.wirecard.resource;

import java.net.URL;

public class StorageInitRequest {
    private String shopId;
    private String orderIdent;
    private URL returnUrl;
    private LanguageType language;
    private URL iframeCssUrl;
    private boolean iframe;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

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

    public LanguageType getLanguage() {
        return language;
    }

    public void setLanguage(LanguageType language) {
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
