package com.andreyjig.clothingstore.network;

import com.andreyjig.clothingstore.entity.shell.CartShell;
import com.andreyjig.clothingstore.entity.shell.ProductShell;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StoreJSON {

    @GET("/cart.json")
    Call<CartShell> getCart();

    @GET("/products/{id}.json")
    Call<ProductShell> getProduct(@Path("id") int id);
}
