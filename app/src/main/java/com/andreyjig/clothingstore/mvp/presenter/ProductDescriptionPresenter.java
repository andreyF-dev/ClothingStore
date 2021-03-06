package com.andreyjig.clothingstore.mvp.presenter;

import androidx.annotation.NonNull;
import com.andreyjig.clothingstore.mvp.model.ProductModel;
import com.andreyjig.clothingstore.mvp.model.handler.DataHandler;
import com.andreyjig.clothingstore.ui.fragment.ProductDescriptionFragmentArgs;
import com.andreyjig.clothingstore.mvp.view.ProductDescriptionView;
import com.andreyjig.clothingstore.entity.Product;
import com.andreyjig.clothingstore.entity.product.Color;
import com.andreyjig.clothingstore.entity.product.Image;
import com.andreyjig.clothingstore.entity.product.Size;
import com.andreyjig.clothingstore.entity.product.Variant;
import com.andreyjig.clothingstore.util.ProductHelper;
import com.arellomobile.mvp.InjectViewState;
import java.util.ArrayList;

@InjectViewState
public class ProductDescriptionPresenter extends BasePresenter<ProductDescriptionView> {

    private String title;
    private Product product;
    private Variant variant;
    private ArrayList<Color> colors;
    private ArrayList<Size> sizes;
    private ArrayList<Image> images;
    private int productId;
    private int variantId;
    private int colorId;
    private int sizeId;
    private ProductModel model;

    public ProductDescriptionPresenter(ProductDescriptionFragmentArgs args) {
        productId = args.getProductId();
        variantId = args.getVariantId();
        title = args.getName();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        setTitle(title);
        model = new ProductModel(productId);
    }

    @Override
    public void attachView(ProductDescriptionView view) {
        super.attachView(view);
        if (product != null) {
            setColors();
        } else {
            setPreview();
        }
    }

    private void setPreview(){
        getViewState().showProgressBar();
        Product product = model.getCachedData();
        updateProduct(product);
        getData();
    }

    @Override
    public void getData() {
        DataHandler<Product> handler = new DataHandler<Product>() {
            @Override
            public void setDownloadedData(Product data) {
                updateProduct(data);
            }
            @Override
            public void setErrorDownloaded(int errorStringId) {
                setErrorDialog(errorStringId);
            }
        };
        model.downloadData(handler);
    }

    private void updateProduct (Product product){
        if (product != null){
            if (this.product == null){
                try {
                    setDefaultVariant(product);
                    setProduct(product);
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else if (!product.equals(this.product)){
                setProduct(product);
            }
        }
    }

    private void setDefaultVariant(Product product){
        variant = ProductHelper.getVariant(product, variantId);
        colorId = variant.getColorId();
        sizeId = variant.getSizeId();
    }

    public void setProduct(@NonNull Product product) {
        getViewState().hideProgressBar();
        this.product = product;
        model.setDataToCache(product);
        setProductDescription();
        setColors();
    }

    private void setProductDescription() {
        getViewState().updateProduct(product);
    }

    private void setColors() {
        colors = ProductHelper.getAllColor(product);
        getViewState().updateColors(colors);
        int indexCurrentColor = ProductHelper.getIndexColorById(colors, colorId);
        setColor(indexCurrentColor);
    }


    public void setColor(int index) {
        colorId = colors.get(index).getId();
        setColorDrawer(index);
    }

    private void setColorDrawer(int index) {
        String color = colors.get(index).getHashCode();
        getViewState().updateColorDrawer(color);
        setSizes();
    }

    private void setSizes() {
        sizes = ProductHelper.getAllSizes(product, colorId);
        getViewState().updateSizes(sizes);
        int indexCurrentSize = ProductHelper.getIndexSizeById(sizes, sizeId);
        setSize(indexCurrentSize);
    }

    public void setSize(int index) {
        sizeId = sizes.get(index).getId();
        setVariant();
    }

    private void setVariant() {
        variant = ProductHelper.getVariant(product, colorId, sizeId);
        setNameProduct();
        setImages();
    }

    private void setNameProduct() {
        if (!variant.getName().isEmpty()) {
            getViewState().updateVariantName(variant.getName());
        } else {
            getViewState().updateVariantName(product.getName());
        }
    }

    private void setImages() {
        ArrayList<Image> newImages = new ArrayList<>(variant.getPhotos());
        if (images == null || !images.equals(newImages)) {
            images = newImages;
            getViewState().updateImages(images);
        }
    }
}
