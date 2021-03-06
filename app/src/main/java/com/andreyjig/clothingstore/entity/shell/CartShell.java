package com.andreyjig.clothingstore.entity.shell;

import com.andreyjig.clothingstore.entity.Cart;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartShell {

    @SerializedName("cart")
    @Expose
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
