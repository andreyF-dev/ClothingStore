package com.andreyjig.clothingstore.model.product;

import java.util.Objects;

public class ProductProperties {

    private ProductColor color;
    private String size;
    private String name;


    public ProductProperties() {
    }

    public ProductProperties(ProductColor color, String size, String name) {
        this.color = color;
        this.size = size;
        this.name = name;
    }

    public ProductColor getColor() {
        return color;
    }

    public void setColor(ProductColor color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductProperties that = (ProductProperties) o;
        return Objects.equals(color, that.color) &&
                size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size);
    }
}
