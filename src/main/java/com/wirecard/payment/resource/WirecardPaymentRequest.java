package com.wirecard.payment.resource;

public class WirecardPaymentRequest {

    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "WirecardPaymentRequest{"
                + "shopId='" + shopId + '\''
                + '}';
    }
}
