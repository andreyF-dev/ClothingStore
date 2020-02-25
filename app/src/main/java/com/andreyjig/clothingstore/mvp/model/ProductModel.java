package com.andreyjig.clothingstore.mvp.model;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.Product;
import com.andreyjig.clothingstore.entity.shell.ProductShell;
import com.andreyjig.clothingstore.mvp.model.handler.DataHandler;
import com.andreyjig.clothingstore.network.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductModel extends BaseModel<Product>{

    private int id;

    public ProductModel (int id){
        this.id = id;
    }

    @Override
    public void downloadData(DataHandler<Product> handler) {
        NetworkService.getInstance()
                .getJSONApi()
                .getProduct(id)
                .enqueue(new Callback<ProductShell>() {
                    @Override
                    public void onResponse(Call<ProductShell> call, Response<ProductShell> response) {
                        try {
                            Product product = response.body().getProduct();
                            handler.setDownloadedData(product);
                            setDataToCache(product);
                        } catch (Exception e){
                            e.printStackTrace();
                            handler.setErrorDownloaded(R.string.error_get_message);
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductShell> call, Throwable t) {
                        t.printStackTrace();
                        handler.setErrorDownloaded(R.string.error_no_get_message);
                    }
                });
    }

    @Override
    public Product getCachedData() {
        return realmHelper.getCachedProduct(id);
    }

    @Override
    public void setDataToCache(Product data) {
        realmHelper.setCashedProduct(data);
    }
}
