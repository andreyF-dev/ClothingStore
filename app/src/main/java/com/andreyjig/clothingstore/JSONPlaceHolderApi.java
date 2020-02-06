package com.andreyjig.clothingstore;

import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.model.Product;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @Headers("cart")
    @GET("/cart.json")
    public Call<Cart> getCart();

    @GET("/products/{id}.json")
    public Call<Product> getProduct(@Path("id") int id);
}
