package com.andreyjig.clothingstore.entity.handler;

import com.andreyjig.clothingstore.entity.Cart;

public interface CartHandler extends ErrorDownload{
    void setDownloadedCart(Cart cart);
}
