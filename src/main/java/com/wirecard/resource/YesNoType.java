package com.wirecard.resource;

public enum YesNoType {
    YES (true,"yes"),
    NO (false, "no");

    YesNoType(boolean type, String value) {
        this.type = type;
        this.value = value;
    }
    private boolean type;
    private String value;

    public boolean isType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
