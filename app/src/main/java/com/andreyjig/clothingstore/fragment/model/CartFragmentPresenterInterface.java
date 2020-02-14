package com.andreyjig.clothingstore.fragment.model;

import com.andreyjig.clothingstore.model.Cart;

public interface CartFragmentPresenterInterface {
    void setErrorDialog();
    void setCart(Cart cart);
}
