package com.wirecard.resource;

public enum PaymentType {
    SELECT("SELECT", "Select", "user may select but only on Wirecard Checkout Page"),
    BANCONTACT_MISTERCASH("BANCONTACT_MISTERCASH", "Bancontact/Mister Cash", ""),
    CCARD("CCARD", "Credit Card, Maestro SecureCode", ""),
    CCARD_MOTO("CCARD-MOTO", "Credit Card - Mail Order and Telephone Order", ""),
    EKONTO("EKONTO", "eKonto", ""),
    EPAY_BG("EPAY_BG", "ePay.bg", ""),
    EPS("EPS", "eps Online-Überweisung", ""),
    GIROPAY("GIROPAY", "giropay", ""),
    IDL("IDL", "iDEAL", ""),
    INSTALLMENT("INSTALLMENT", "Installment by payolution or RatePAY", ""),
    INVOICE("INVOICE", "Invoice by payolution or RatePAY", ""),
    MONETA("MONETA", "moneta.ru", ""),
    MPASS("MPASS", "mpass", ""),
    PRZELEWY24("PRZELEWY24", "Przelewy24", ""),
    PAYPAL("PAYPAL", "PayPal", ""),
    PBX("PBX", "paybox", ""),
    POLI("POLI", "POLi", ""),
    PSC("PSC", "paysafecard", ""),
    QUICK("QUICK", "@Quick", ""),
    SEPA_DD("SEPA-DD", "SEPA Direct Debit", ""),
    SKRILLDIRECT("SKRILLDIRECT", "Skrill Direct", ""),
    SKRILLWALLET("SKRILLWALLET", "Skrill Digital Wallet", ""),
    SOFORTUEBERWEISUNG("SOFORTUEBERWEISUNG", "SOFORT Banking", ""),
    TATRAPAY("TATRAPAY", "TatraPay", ""),
    TRUSTLY("TRUSTLY", "Trustly", ""),
    TRUSTPAY("TRUSTPAY", "TrustPay", ""),
    VOUCHER("VOUCHER", "My Voucher", "");

    private String type, name, description;

    PaymentType(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return type;
    }
}
