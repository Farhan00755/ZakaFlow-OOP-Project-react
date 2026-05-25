package com.zakaflow.zakaflow.model;

public enum ZakatType {
    FITRAH("Zakat Fitrah"),
    MAAL("Zakat Maal"),
    PENGHASILAN("Zakat Penghasilan (Profesi)"),
    PERDAGANGAN("Zakat Perdagangan"),
    EMAS_PERAK("Zakat Emas & Perak");

    private final String label;

    ZakatType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
