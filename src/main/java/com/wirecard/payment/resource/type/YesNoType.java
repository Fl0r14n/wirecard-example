package com.wirecard.payment.resource.type;

public enum YesNoType {

    YES(true, "yes"),
    NO(false, "no");

    YesNoType(boolean booleanValue, String value) {
        this.booleanValue = booleanValue;
        this.value = value;
    }
    private final boolean booleanValue;
    private final String value;

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static YesNoType valueOfDescription(String description) {
        for(YesNoType v: values()) {
            if(v.value.equalsIgnoreCase(description)) {
                return v;
            }
        }
        return null;
    }
}
