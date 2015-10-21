package com.wirecard.resource.response;

import com.wirecard.resource.WirecardResponse;
import com.wirecard.resource.type.FinancialInstitutionType;
import com.wirecard.resource.type.PaymentType;

public class ReadStorageResponse extends WirecardResponse {

    private String anonymousPan;
    private String maskedPan;
    private FinancialInstitutionType financialInstitution;
    private String brand; //creditcard brand
    private String cardholdername;
    private String expiry;
    private PaymentType paymentType;

    public String getAnonymousPan() {
        return anonymousPan;
    }

    public void setAnonymousPan(String anonymousPan) {
        this.anonymousPan = anonymousPan;
    }

    public String getMaskedPan() {
        return maskedPan;
    }

    public void setMaskedPan(String maskedPan) {
        this.maskedPan = maskedPan;
    }

    public FinancialInstitutionType getFinancialInstitution() {
        return financialInstitution;
    }

    public void setFinancialInstitution(FinancialInstitutionType financialInstitution) {
        this.financialInstitution = financialInstitution;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCardholdername() {
        return cardholdername;
    }

    public void setCardholdername(String cardholdername) {
        this.cardholdername = cardholdername;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "ReadStorageResponse{" +
                "anonymousPan='" + anonymousPan + '\'' +
                ", maskedPan='" + maskedPan + '\'' +
                ", financialInstitution=" + financialInstitution +
                ", brand='" + brand + '\'' +
                ", cardholdername='" + cardholdername + '\'' +
                ", expiry='" + expiry + '\'' +
                ", paymentType=" + paymentType +
                "\n" + super.toString() +
                "}";
    }
}
