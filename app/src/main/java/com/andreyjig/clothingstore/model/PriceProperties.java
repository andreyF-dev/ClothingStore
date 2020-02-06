package com.andreyjig.clothingstore.model;

import java.util.Objects;

public class PriceProperties {

    private String color;
    private Size size;

    public PriceProperties() {
    }

    public PriceProperties(String color, Size size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceProperties that = (PriceProperties) o;
        return Objects.equals(color, that.color) &&
                size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size);
    }
}
