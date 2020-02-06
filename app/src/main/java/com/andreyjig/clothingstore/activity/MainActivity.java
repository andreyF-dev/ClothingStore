package com.andreyjig.clothingstore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.andreyjig.clothingstore.NetworkService;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.model.Cart;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkService.newInstance()
                .getJSONApi()
                .getCart()
                .enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        Cart cart = response.body();
                        Log.d("MyRetrofit", "cards " + cart.toString());
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        Log.d("MyRetrofit", t.toString());
                    }
                });
    }
}
