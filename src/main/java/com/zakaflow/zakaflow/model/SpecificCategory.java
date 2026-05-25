package com.zakaflow.zakaflow.model;

public enum SpecificCategory {
    PENDIDIKAN("Pendidikan"),
    KESEHATAN("Kesehatan"),
    KEMANUSIAAN("Kemanusiaan"),
    BENCANA_ALAM("Bencana Alam"),
    PEMBANGUNAN_MASJID("Pembangunan Masjid"),
    EKONOMI("Ekonomi & UMKM"),
    LINGKUNGAN("Lingkungan"),
    LAINNYA("Lainnya");

    private final String label;

    SpecificCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
