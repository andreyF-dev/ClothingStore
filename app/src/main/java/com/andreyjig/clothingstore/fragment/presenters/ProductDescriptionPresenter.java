package com.andreyjig.clothingstore.fragment.presenters;

import android.util.Log;
import android.view.View;

import com.andreyjig.clothingstore.fragment.ProductDescriptionFragmentArgs;
import com.andreyjig.clothingstore.fragment.views.ErrorHandlerView;
import com.andreyjig.clothingstore.fragment.views.ProductDescriptionView;
import com.andreyjig.clothingstore.activity.views.TitleHandlerView;
import com.andreyjig.clothingstore.model.Product;
import com.andreyjig.clothingstore.model.product.Color;
import com.andreyjig.clothingstore.model.product.Image;
import com.andreyjig.clothingstore.model.product.Size;
import com.andreyjig.clothingstore.model.product.Variant;
import com.andreyjig.clothingstore.utils.NetworkService;
import com.andreyjig.clothingstore.utils.ProductHelper;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.MvpViewState;

import java.util.ArrayList;

@InjectViewState
public class ProductDescriptionPresenter extends MvpPresenter<ProductDescriptionView> {

    private final int NO_IMAGE = -2;
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
    private int imageIndex;
    private ErrorHandlerView errorHandlerView;
    private TitleHandlerView titleHandlerView;

    public ProductDescriptionPresenter(ProductDescriptionFragmentArgs args,
                                       ErrorHandlerView errorHandlerView,
                                       TitleHandlerView titleHandlerView) {
        this.errorHandlerView = errorHandlerView;
        this.titleHandlerView = titleHandlerView;
        productId = args.getProductId();
        variantId = args.getVariantId();
        title = args.getName();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        titleHandlerView.getTitle(title);
        getViewState().progressBarVisibility();
        getProduct();
    }

    @Override
    public void attachView(ProductDescriptionView view) {
        super.attachView(view);
        if (product != null) {
            setProductDescription();
        }
    }

    private void getProduct() {
        NetworkService.getInstance().getProduct(this::setProduct, productId);
    }

    public void setProduct(Product product) {
        if (product != null) {
            getViewState().progressBarInvisibility();
            this.product = product;
            variant = ProductHelper.getVariant(product, variantId);
            colorId = variant.getColorId();
            sizeId = variant.getSizeId();
            setProductDescription();
        } else {
            errorHandlerView.getErrorDialog(this::getProduct);
        }
    }

    private void setProductDescription(){
        getViewState().setProduct(product);
        getColors();
    }

    public void getColors() {
        colors = ProductHelper.getAllColor(product);
        int index = ProductHelper.getIndexByIndex(new ArrayList<>(colors), colorId);
        getViewState().setColors(colors);
        getViewState().setColor(index);
    }

    public void setColor(int index) {
        colorId = colors.get(index).getId();
        String color = colors.get(index).getHashCode();
        getViewState().setColorDrawer(color);
        getSize();
    }

    public void getSize() {
        sizes = ProductHelper.getAllSizes(product, colorId);
        int index = ProductHelper.getIndexByIndex(new ArrayList<>(sizes), sizeId);
        getViewState().setSizes(sizes);
        getViewState().setSize(index);
    }

    public void setSize(int index) {
        sizeId = sizes.get(index).getId();
        setVariant();
    }

    private void setVariant() {
        variant = ProductHelper.getVariant(product, colorId, sizeId);
        setNameProduct();
        getImage();
    }

    private void setNameProduct() {
        if (!variant.getName().isEmpty()) {
            getViewState().setName(variant.getName());
        } else {
            getViewState().setName(product.getName());
        }
    }

    private void getImage() {
        if (images == null || !images.equals(variant.getPhotos())) {
            images = variant.getPhotos();
            if (images != null && images.size() > 0) {
                imageIndex = 0;
            } else {
                imageIndex = NO_IMAGE;
            }
        }
        setImage(0);
    }

    public void setImage(int step) {
        if (imageIndex != NO_IMAGE) {
            getViewState().imageButtonVisibility(View.VISIBLE);
            imageIndex = (imageIndex + step) % images.size();
            if (imageIndex == -1) {
                imageIndex = images.size() - 1;
            }
            String imageUrl = images.get(imageIndex).getBig();
            Log.d("Retrofit", "imageIndex in= " + imageIndex);
            getViewState().setImage(imageUrl);
        } else {
            getViewState().imageButtonVisibility(View.INVISIBLE);
            getViewState().setDefaultImage();
        }
    }
}
