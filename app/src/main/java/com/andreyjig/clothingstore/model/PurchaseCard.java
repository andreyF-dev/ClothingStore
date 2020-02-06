package com.andreyjig.clothingstore.model;

import android.graphics.Color;

import com.andreyjig.clothingstore.model.product.Product;

public class PurchaseCard {

    private Product product;
    private int count;
    private Color color;
    private String size;

    public PurchaseCard() {
    }

    public PurchaseCard(Product product, int count, Color color, String size) {
        this.product = product;
        this.count = count;
        this.color = color;
        this.size = size;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
