package com.andreyjig.clothingstore.mvp.model;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.Cart;
import com.andreyjig.clothingstore.mvp.model.handler.CartHandler;
import com.andreyjig.clothingstore.entity.shell.CartShell;
import com.andreyjig.clothingstore.network.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartModel {

    public void getCart(CartHandler cartHandler) {
        NetworkService.getInstance()
                .getJSONApi()
                .getCart()
                .enqueue(new Callback<CartShell>() {
                    @Override
                    public void onResponse(Call<CartShell> call, Response<CartShell> response) {
                        try {
                            Cart cart = response.body().getCart();
                            cartHandler.setDownloadedCart(cart);
                        } catch (Exception e){
                            e.printStackTrace();
                            cartHandler.setErrorDownloaded(R.string.error_get_message);
                        }
                    }

                    @Override
                    public void onFailure(Call<CartShell> call, Throwable t) {
                        t.printStackTrace();
                        cartHandler.setErrorDownloaded(R.string.error_no_get_message);
                    }
                });
    }
}