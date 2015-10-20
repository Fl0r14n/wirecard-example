package com.wirecard.resource.type;

public enum FinancialInstitutionType {

    BANCOMAT_MISTER_CASH("Bancontact/Mister Cash", "Bancontact/Mister Cash"),
    MASTERCARD("MC", "MAsterCard"),
    MAESTRO("Maestro", "Maestro SecureCode"),
    VISA("Visa", "Visa");
    //TODO

    FinancialInstitutionType(String value, String description) {
        this.value = value;
        this.description = description;
    }
    private String value, description;

    @Override
    public String toString() {
        return value;
    }

    public static FinancialInstitutionType valueOfType(String value) {
        for (FinancialInstitutionType v : values()) {
            if(v.value.equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No enum const " + FinancialInstitutionType.class + "@type." + value);
    }
}
