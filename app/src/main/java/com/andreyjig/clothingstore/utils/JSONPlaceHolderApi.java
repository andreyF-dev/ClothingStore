package com.andreyjig.clothingstore.utils;

import com.andreyjig.clothingstore.model.shell.CartShell;
import com.andreyjig.clothingstore.model.Product;
import com.andreyjig.clothingstore.model.shell.ProductShell;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {

    @GET("/cart.json")
    public Call<CartShell> getCart();

    @GET("/products/{id}.json")
    public Call<ProductShell> getProduct(@Path("id") int id);
}
