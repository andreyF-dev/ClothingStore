package com.andreyjig.clothingstore.model.handler;

import com.andreyjig.clothingstore.model.Cart;

public interface CartHandler extends ErrorDownload{
    void setDownloadedCart(Cart cart);
}