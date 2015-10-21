package com.wirecard.resource.type;

public enum YesNoType {

    YES(true, "yes"),
    NO(false, "no");

    YesNoType(boolean type, String value) {
        this.type = type;
        this.value = value;
    }
    private final boolean type;
    private final String value;

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
    
    public static YesNoType valueOfDescription(String description) {
        for(YesNoType v: values()) {
            if(v.value.equalsIgnoreCase(description)) {
                return v;
            }
        }
        return null;
    }
}
