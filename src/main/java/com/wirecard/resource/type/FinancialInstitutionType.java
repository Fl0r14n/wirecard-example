package com.wirecard.resource.type;

public enum FinancialInstitutionType {

    //BANCONTACT_MISTERCASH
    BANCOMAT_MISTER_CASH("Bancontact/Mister Cash", "Bancontact/Mister Cash"),
    //CCARD / CCARD-MOTO
    MASTERCARD("MC", "MAsterCard"),
    MAESTRO("Maestro", "Maestro SecureCode"),
    VISA("Visa", "Visa"),
    VISA_VERIFIED("VbV", "Verified by Visa"),
    AMEX("Amex", "American Express"),
    AMERICAN_EXPRES_SAFEKEY("amexsafekey", "American Express SafeKey"),
    DINERS("Diners", "Diners Club"),
    DISCOVER("Discover", "Discover"),
    JCB("JCB", "JCB"),
    UATP("UATP", "Universal Airline Travel Plan"),
    //EKONTO
    EKONTO("eKonto", "eKonto"),
    //EPAY_BG
    EPAY("ePay.bg", "ePay.bg"),
    //EPS
    ARZ_AAB("ARZ|AAB", "Austrian Anadi Bank AG"),
    BA_CA("BA-CA", "Bank Austria"),
    BB_RACON("BB-Racon", "Bank Burgenland"),
    ARZ_BD("ARZ|BD", "bankdirekt.at AG"),
    ARZ_BAF("ARZ|BAF", "Bank für Ärzte und Freie Berufe"),
    ARZ_BCS("ARZ|BCS", "Bankhaus Carl Spängler & Co. AG"),
    ARZ_BSS("ARZ|BSS", "Bankhaus Schelhammer & Schattera AG"),
    BWAG_B("Bawag|B", "BAWAG"),
    ARZ_BKS("ARZ|BKS", "BKS Bank AG"),
    ARZ_BTV("ARZ|BTV", "BTV VIER LÄNDER BANK"),
    ARZ_VB("ARZ|VB", "Die österreichischen Volksbanken"),
    BAWAG_EASY("Bawag|E", "easybank"),
    SPARDAT_EBS("Spardat|EBS", "Erste Bank und Sparkassen"),
    ARZ_GB("ARZ|GB", "Gärtnerbank"),
    ARZ_HAA("ARZ|HAA", "Hypo Alpe-Adria-Bank International AG"),
    ARZ_HI("ARZ|HI", "Hypo Investmentbank AG"),
    HYPO_O("Hypo-Racon|O", "Hypo Oberösterreich"),
    HYPO_S("Hypo-Racon|S", "Hypo Salzburg"),
    HYPO_ST("Hypo-Racon|ST", "Hypo Steiermark"),
    ARZ_HTB("ARZ|HTB", "Hypo Tirol Bank AG"),
    ARZ_IB("ARZ|IB", "Immo-Bank"),
    ARZ_IKB("ARZ|IKB", "Investkredit Bank AG"),
    ARZ_NLH("ARZ|NLH", "Niederösterreichische Landes-Hypothekenbank AG"),
    ARZ_OB("ARZ|OB", "Oberbank AG"),
    ARZ_AB("ARZ|AB", "Österreichische Apothekerbank"),
    ARZ_PB("ARZ|PB", "PRIVAT BANK AG"),
    BAWAG_P("Bawag|P", "PSK Bank"),
    RAIFFEISEN_BANK("Racon", "Raiffeisen Bank"),
    BAWAG_S("Bawag|S", "Sparda Bank"),
    ARZ_SBL("ARZ|SBL", "Sparda-Bank Linz"),
    ARZ_SBVI("ARZ|SBVI", "Sparda-Bank Villach/Innsbruck"),
    ARZ_VLH("ARZ|VLH", "Vorarlberger Landes- und Hypothekerbank AG"),
    ARZ_SB("ARZ|SB", "Schoellerbank AG"),
    ARZ_VKB("ARZ|VKB", "Volkskreditbank AG"),
    ARZ_VRB("ARZ|VRB", "VR-Bank Braunau"),
    //GIROPAY
    GIROPAY("GIROPAY", "giropay"),
    //IDL
    ABN_AMRO_BANK("ABNAMROBANK", "ABN AMRO Bank"),
    ASN_BANK("ASNBANK", "ASN Bank"),
    ING_BANK("INGBANK", "ING"),
    KNAB("KNAB", "Knab"),
    RABO_BANK("RABOBANK", "Rabobank"),
    SNS_BANK("SNSBANK", "SNS Bank"),
    REGIO_BANK("REGIOBANK", "Regio Bank"),
    TRIODOS_BANK("TRIODOSBANK", "Triodos Bank"),
    VANLANSCHOT("VANLANSCHOT", "Van Lanschot Bankiers"),
    //INVOICE/INSTALLMENT
    PAYOLUTION("payolution", "payolution"),
    RATEPAY("RatePAY", "RatePAY"),
    //MONETA
    MONETA("moneta.ru", "moneta.ru"),
    //MPASS
    MPASS("mpass", "mpass"),
    //PAYPAL
    PAYPAL("PAYPAL", "PayPal"),
    //PBX
    PBX("PBX", "Mobile Phone Invoice"),
    //POLI
    POLI("POLi", "POLi"),
    //PRZELEWY24
    PRZELEWY24("Przelewy24", "Przelewy24"),
    //QUICK
    QUICK("QUICK", "@Quick"),
    //SEPA-DD
    SEPA_DD("SEPA-DD", "SEPA Direct Debit"),
    //SKRILLDIRECT
    SKRILL_DIRECT("Skrill Direct", "Skrill Direct"),
    //SKRILLWALLET
    SKRILL_WALLET("Skrill Digital Wallet", "Skrill Digital Wallet"),
    //SOFORTUEBERWEISUNG
    SOFORTUEBERWEISUNG("SOFORTUEBERWEISUNG", "sofortüberweisung"),
    //TATRAPAY
    TATRAPAY("TatraPay", "TatraPay"),
    //TRUSTLY
    TRUSTLY("TRUSTLY", "Trustly"),
    //TRUSTPAY
    TRUSTPAY("Bank ID of used bank", "TrustPay"),
    //VOUCHER
    MY_VOUCHER("ValueMaster", "My Voucher");

    FinancialInstitutionType(String value, String description) {
        this.value = value;
        this.description = description;
    }
    private final String value, description;

    @Override
    public String toString() {
        return value;
    }

    private String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static FinancialInstitutionType valueOfType(String value) {
        for (FinancialInstitutionType v : values()) {
            if (v.value.equals(value)) {
                return v;
            }
        }
        return null;
    }
}
