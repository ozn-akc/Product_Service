package de.seven.product.domain.model;

import lombok.Getter;

@Getter
public enum Unit {
    EURO("€"),
    DOLLAR("$");

    private final String value;

    Unit(String value) {
        this.value = value;
    }

}
