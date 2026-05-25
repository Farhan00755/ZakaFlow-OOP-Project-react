package com.zakaflow.zakaflow.model;

public enum Asnaf {
    FAKIR("Fakir"),
    MISKIN("Miskin"),
    AMIL("Amil"),
    MUALLAF("Muallaf"),
    RIQAB("Riqab"),
    GHARIMIN("Gharimin"),
    FISABILILLAH("Fisabilillah"),
    IBNU_SABIL("Ibnu Sabil");

    private final String label;

    Asnaf(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
