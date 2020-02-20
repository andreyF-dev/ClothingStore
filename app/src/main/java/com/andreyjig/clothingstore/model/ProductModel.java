package com.andreyjig.clothingstore.model;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.Product;
import com.andreyjig.clothingstore.entity.handler.ProductDescription;
import com.andreyjig.clothingstore.entity.shell.ProductShell;
import com.andreyjig.clothingstore.util.NetworkService;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductModel {

    private static ProductModel instance;
    private Realm realm;

    public static ProductModel getInstance() {
        if (instance == null) {
            instance = new ProductModel();
        }
        return instance;
    }

    private ProductModel() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("product.realm")
                .schemaVersion(1)
                .build();
        realm = Realm.getInstance(config);
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

    public Product getCachedProduct(int id){
        return realm.where(Product.class).equalTo("id", id).findFirst();
    }

    public void setCashedProduct(Product product){
        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(product);
        });
    }

    public void closeProductModel(){
        realm.close();
        instance = null;
    }
}
