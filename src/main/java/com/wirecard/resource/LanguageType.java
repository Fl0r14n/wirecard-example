package com.wirecard.resource;

public enum LanguageType {

    AR("ar", "Arabian"),
    BS("bs", "Bosnian"),
    BG("bg", "Bulgarian"),
    ZH("zh", "Chinese"),
    HR("hr", "Croatian"),
    CS("cs", "Czech"),
    DA("da", "Danish"),
    NL("nl", "Dutch"),
    EN("en", "English"),
    ET("et", "Estonian"),
    FI("fi", "Finnish"),
    FR("fr", "French"),
    DE("de", "German"),
    EL("el", "Greek"),
    HE("he", "Hebrew"),
    HI("hi", "Hindi"),
    HU("hu", "Hungarian"),
    IT("it", "Italian"),
    JA("ja", "Japanese"),
    KO("ko", "Korean"),
    LV("lv", "Latvian"),
    LT("lt", "Lithuanian"),
    MK("mk", "Macedonian"),
    NO("no", "Norwegian"),
    PL("pl", "Polish"),
    PT("pt", "Portuguese"),
    RO("ro", "Romanian"),
    RU("ru", "Russian"),
    SR("sr", "Serbian"),
    SK("sk", "Slovakian"),
    SL("sl", "Slovenian"),
    ES("es", "Spanish"),
    SV("sv", "Swedish"),
    TR("tr", "Turkish"),
    UK("uk", "Ukrainian");

    private String type, language;

    LanguageType(String type, String language) {
        this.type = type;
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return type;
    }
}
