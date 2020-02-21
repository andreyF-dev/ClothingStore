package com.andreyjig.clothingstore.mvp.model;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.Cart;
import com.andreyjig.clothingstore.entity.shell.CartShell;
import com.andreyjig.clothingstore.mvp.model.handler.DataHandler;
import com.andreyjig.clothingstore.network.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartModel extends BaseModel<Cart>{


    @Override
    public void downloadData(DataHandler<Cart> handler) {
        NetworkService.getInstance()
                .getJSONApi()
                .getCart()
                .enqueue(new Callback<CartShell>() {
                    @Override
                    public void onResponse(Call<CartShell> call, Response<CartShell> response) {
                        try {
                            Cart cart = response.body().getCart();
                            handler.setDownloadedData(cart);
                        } catch (Exception e){
                            e.printStackTrace();
                            handler.setErrorDownloaded(R.string.error_get_message);
                        }
                    }

                    @Override
                    public void onFailure(Call<CartShell> call, Throwable t) {
                        t.printStackTrace();
                        handler.setErrorDownloaded(R.string.error_no_get_message);
                    }
                });
    }

    @Override
    public Cart getCachedData() {
        return realmHelper.getCachedCart();
    }

    @Override
    public void setDataToCache(Cart data) {
        realmHelper.setCashedCart(data);
    }
}
