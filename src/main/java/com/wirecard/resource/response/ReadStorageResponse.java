package com.wirecard.resource.response;

import com.wirecard.resource.WirecardResponse;
import com.wirecard.resource.type.FinancialInstitutionType;

public class ReadStorageResponse extends WirecardResponse {
    private String anonymousPan;
    private String maskedPan;
    private FinancialInstitutionType financialInstitution;
    private String brand; //brand of credit card
    private String cardholdername;
    private String expiry;
    private String accountOwner;
    private String bankName;
    private String bankCountry;
    private String bankAccount;
    private String bankNumber;
    private String bankBic;
    private String bankAccountIban;
    private String payerPayboxNumber;


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

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCountry() {
        return bankCountry;
    }

    public void setBankCountry(String bankCountry) {
        this.bankCountry = bankCountry;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankBic() {
        return bankBic;
    }

    public void setBankBic(String bankBic) {
        this.bankBic = bankBic;
    }

    public String getBankAccountIban() {
        return bankAccountIban;
    }

    public void setBankAccountIban(String bankAccountIban) {
        this.bankAccountIban = bankAccountIban;
    }

    public String getPayerPayboxNumber() {
        return payerPayboxNumber;
    }

    public void setPayerPayboxNumber(String payerPayboxNumber) {
        this.payerPayboxNumber = payerPayboxNumber;
    }
}
