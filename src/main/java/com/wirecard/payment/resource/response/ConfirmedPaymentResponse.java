package com.wirecard.payment.resource.response;

import com.wirecard.payment.resource.type.PaymentStateType;
import com.wirecard.payment.resource.type.YesNoType;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class ConfirmedPaymentResponse extends ReadStorageResponse {

    private BigDecimal amount;
    private Currency currency;
    private Locale language;
    private Long orderNumber;
    private PaymentStateType paymentState;
    private YesNoType authenticated;
    private String gatewayReferenceNumber;
    private String gatewayContractNumber;
    private String avsResponseCode;
    private String avsResponseMessage;
    private String avsProviderResultCode;
    private String avsProviderResultMessage;
    
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public PaymentStateType getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentStateType paymentState) {
        this.paymentState = paymentState;
    }

    public YesNoType getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(YesNoType authenticated) {
        this.authenticated = authenticated;
    }

    public String getGatewayReferenceNumber() {
        return gatewayReferenceNumber;
    }

    public void setGatewayReferenceNumber(String gatewayReferenceNumber) {
        this.gatewayReferenceNumber = gatewayReferenceNumber;
    }

    public String getGatewayContractNumber() {
        return gatewayContractNumber;
    }

    public void setGatewayContractNumber(String gatewayContractNumber) {
        this.gatewayContractNumber = gatewayContractNumber;
    }

    public String getAvsResponseCode() {
        return avsResponseCode;
    }

    public void setAvsResponseCode(String avsResponseCode) {
        this.avsResponseCode = avsResponseCode;
    }

    public String getAvsResponseMessage() {
        return avsResponseMessage;
    }

    public void setAvsResponseMessage(String avsResponseMessage) {
        this.avsResponseMessage = avsResponseMessage;
    }

    public String getAvsProviderResultCode() {
        return avsProviderResultCode;
    }

    public void setAvsProviderResultCode(String avsProviderResultCode) {
        this.avsProviderResultCode = avsProviderResultCode;
    }

    public String getAvsProviderResultMessage() {
        return avsProviderResultMessage;
    }

    public void setAvsProviderResultMessage(String avsProviderResultMessage) {
        this.avsProviderResultMessage = avsProviderResultMessage;
    }

    @Override
    public String toString() {
        return "ConfirmedPaymentResponse{" +
                "amount=" + amount +
                ", currency=" + currency +
                ", language=" + language +
                ", orderNumber='" + orderNumber + '\'' +
                ", paymentState=" + paymentState +
                ", authenticated=" + authenticated +
                ", gatewayReferenceNumber='" + gatewayReferenceNumber + '\'' +
                ", gatewayContractNumber='" + gatewayContractNumber + '\'' +
                ", avsResponseCode='" + avsResponseCode + '\'' +
                ", avsResponseMessage='" + avsResponseMessage + '\'' +
                ", avsProviderResultCode='" + avsProviderResultCode + '\'' +
                ", avsProviderResultMessage='" + avsProviderResultMessage + '\'' +
                "\n" + super.toString() +
                "}";
    }
}
