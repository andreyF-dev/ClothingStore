package com.andreyjig.clothingstore.model;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.Cart;
import com.andreyjig.clothingstore.entity.handler.CartHandler;
import com.andreyjig.clothingstore.entity.shell.CartShell;
import com.andreyjig.clothingstore.util.NetworkService;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartModel {

    private static CartModel instance;
    private Realm realm;

    public static CartModel getInstance() {
        if (instance == null) {
            instance = new CartModel();
        }
        return instance;
    }

    private CartModel() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("cart.realm")
                .schemaVersion(1)
                .build();
        realm = Realm.getInstance(config);
    }

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

    public Cart getCachedCart(){
        return realm.where(Cart.class).findFirst();
    }

    public void setCashedCart(Cart cart){
        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(cart);
        });
    }

    public void closeCartModel(){
        realm.close();
        instance = null;
    }
}
