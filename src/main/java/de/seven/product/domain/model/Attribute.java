package de.seven.product.domain.model;

public enum Attribute {
    KUECHE("Küche"),
    WLAN("WLAN"),
    ARBEITSPLATZ("Arbeitsplatz"),
    TV("TV"),
    WASCHMASCHINE("Waschmaschine"),
    TROCKNER("Trockner");

    Attribute(String value) {
        this.value = value;
    }

    private final String value;
}
