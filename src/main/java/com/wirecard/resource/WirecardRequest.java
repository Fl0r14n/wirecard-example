package com.wirecard.resource;

public class WirecardRequest {

    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "WirecardRequest{" +
                "shopId='" + shopId + '\'' +
                '}';
    }
}
