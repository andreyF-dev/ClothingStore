package com.andreyjig.clothingstore.utils;

import com.andreyjig.clothingstore.model.shell.CartShell;
import com.andreyjig.clothingstore.model.shell.ProductShell;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductJSON {

    @GET("/cart.json")
    Call<CartShell> getCart();

    @GET("/products/{id}.json")
    Call<ProductShell> getProduct(@Path("id") int id);
}
