package com.andreyjig.clothingstore.model;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.Product;
import com.andreyjig.clothingstore.entity.handler.ProductDescription;
import com.andreyjig.clothingstore.entity.shell.ProductShell;
import com.andreyjig.clothingstore.util.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductModel {
    private static ProductModel instance;

    public static ProductModel getInstance() {
        if (instance == null) {
            instance = new ProductModel();
        }
        return instance;
    }

    private ProductModel() {
    }

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
