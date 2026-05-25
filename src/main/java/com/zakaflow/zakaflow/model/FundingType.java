package com.zakaflow.zakaflow.model;

public enum FundingType {
    ZAKAT("Zakat"),
    INFAQ("Infaq"),
    SEDEKAH("Sedekah"),
    WAKAF("Wakaf"),
    FIDYAH("Fidyah");

    private final String label;

    FundingType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
