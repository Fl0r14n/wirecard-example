package com.wirecard.resource.request;

import com.wirecard.resource.WirecardRequest;
import com.wirecard.resource.type.*;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;

public class InitPaymentRequest extends WirecardRequest {

    private Locale language;
    private PaymentType paymentType;
    private BigDecimal amount;
    private Currency currency;
    private String orderDescription;
    private URL successUrl;
    private URL cancelUrl;
    private URL failureURl;
    private URL serviceURL;
    private URL confirmUrl;
    private String consumerIpAddress; // TODO is ok string?
    private String consumerUserAgent;
    private URL pendingUrl;
    private URL noScriptInfoUrl;
    private Long orderNumber;
    private String windowName;
    private YesNoType duplicateRequestCheck;
    private String customerStatement;
    private String orderReference;
    private TransactionType transactionIdentifier;
    private FinancialInstitutionType financialInstitution;
    private String orderIdent;
    private String storageId;

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

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

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public URL getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(URL successUrl) {
        this.successUrl = successUrl;
    }

    public URL getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(URL cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public URL getFailureURl() {
        return failureURl;
    }

    public void setFailureURl(URL failureURl) {
        this.failureURl = failureURl;
    }

    public URL getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(URL serviceURL) {
        this.serviceURL = serviceURL;
    }

    public URL getConfirmUrl() {
        return confirmUrl;
    }

    public void setConfirmUrl(URL confirmUrl) {
        this.confirmUrl = confirmUrl;
    }

    public String getConsumerIpAddress() {
        return consumerIpAddress;
    }

    public void setConsumerIpAddress(String consumerIpAddress) {
        this.consumerIpAddress = consumerIpAddress;
    }

    public String getConsumerUserAgent() {
        return consumerUserAgent;
    }

    public void setConsumerUserAgent(String consumerUserAgent) {
        this.consumerUserAgent = consumerUserAgent;
    }

    public URL getPendingUrl() {
        return pendingUrl;
    }

    public void setPendingUrl(URL pendingUrl) {
        this.pendingUrl = pendingUrl;
    }

    public URL getNoScriptInfoUrl() {
        return noScriptInfoUrl;
    }

    public void setNoScriptInfoUrl(URL noScriptInfoUrl) {
        this.noScriptInfoUrl = noScriptInfoUrl;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    public YesNoType getDuplicateRequestCheck() {
        return duplicateRequestCheck;
    }

    public void setDuplicateRequestCheck(YesNoType duplicateRequestCheck) {
        this.duplicateRequestCheck = duplicateRequestCheck;
    }

    public String getCustomerStatement() {
        return customerStatement;
    }

    public void setCustomerStatement(String customerStatement) {
        this.customerStatement = customerStatement;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public TransactionType getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public void setTransactionIdentifier(TransactionType transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public FinancialInstitutionType getFinancialInstitution() {
        return financialInstitution;
    }

    public void setFinancialInstitution(FinancialInstitutionType financialInstitution) {
        this.financialInstitution = financialInstitution;
    }

    public String getOrderIdent() {
        return orderIdent;
    }

    public void setOrderIdent(String orderIdent) {
        this.orderIdent = orderIdent;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
}
