package com.andreyjig.clothingstore.entity.shell;

import com.andreyjig.clothingstore.entity.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductShell {

    @SerializedName("product")
    @Expose
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
