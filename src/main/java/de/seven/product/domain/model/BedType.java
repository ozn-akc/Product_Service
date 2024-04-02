package de.seven.product.domain.model;

import lombok.Getter;

@Getter
public enum BedType {
    SCHLAFCOUCH("Schlafcouch"),
    QUEENSIZE_DOPPELBETT("Queensize-Doppelbett");

    BedType(String value) {
        this.value = value;
    }

    private final String value;
}
