package com.andreyjig.clothingstore.utils;

import com.andreyjig.clothingstore.fragment.model.CartHandlerInterface;
import com.andreyjig.clothingstore.fragment.model.ProductDescriptionInterface;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.model.Product;
import com.andreyjig.clothingstore.model.shell.CartShell;
import com.andreyjig.clothingstore.model.shell.ProductShell;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService instance;
    private static String url = "https://sequeniatesttask.s3-eu-west-1.amazonaws.com";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    private ProductJSON getJSONApi() {
        return retrofit.create(ProductJSON.class);
    }

    public void getProduct(ProductDescriptionInterface presenter, int productId) {
        getJSONApi().getProduct(productId)
                .enqueue(new Callback<ProductShell>() {
                    @Override
                    public void onResponse(Call<ProductShell> call, Response<ProductShell> response) {
                        if (response.isSuccessful()) {
                            Product product = response.body().getProduct();
                            presenter.setDownloadedProduct(product);
                        } else {
                            presenter.setDownloadedProduct(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductShell> call, Throwable t) {
                        t.printStackTrace();
                        presenter.setDownloadedProduct(null);
                    }
                });
    }

    public void getCart(CartHandlerInterface presenter) {
        getJSONApi()
                .getCart()
                .enqueue(new Callback<CartShell>() {
                    @Override
                    public void onResponse(Call<CartShell> call, Response<CartShell> response) {

                        if (response.isSuccessful()) {
                            Cart cart = response.body().getCart();
                            presenter.setDownloadedCart(cart);
                        } else {
                            presenter.setDownloadedCart(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<CartShell> call, Throwable t) {
                        t.printStackTrace();
                        presenter.setDownloadedCart(null);
                    }
                });
    }
}
