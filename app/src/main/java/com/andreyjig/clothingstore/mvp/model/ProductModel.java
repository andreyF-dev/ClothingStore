package com.andreyjig.clothingstore.mvp.model;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.Product;
import com.andreyjig.clothingstore.mvp.model.handler.ProductDescription;
import com.andreyjig.clothingstore.entity.shell.ProductShell;
import com.andreyjig.clothingstore.network.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductModel {

    public void getProduct(ProductDescription productDescription, int productId) {
        NetworkService.getInstance()
                .getJSONApi()
                .getProduct(productId)
                .enqueue(new Callback<ProductShell>() {
                    @Override
                    public void onResponse(Call<ProductShell> call, Response<ProductShell> response) {
                        try {
                            Product product = response.body().getProduct();
                            productDescription.setDownloadedProduct(product);
                        } catch (Exception e){
                            e.printStackTrace();
                            productDescription.setErrorDownloaded(R.string.error_get_message);
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductShell> call, Throwable t) {
                        t.printStackTrace();
                        productDescription.setErrorDownloaded(R.string.error_no_get_message);
                    }
                });
    }
}
