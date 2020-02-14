package com.andreyjig.clothingstore.fragment.presenters;

import android.util.Log;
import android.view.View;

import com.andreyjig.clothingstore.fragment.ProductDescriptionFragmentArgs;
import com.andreyjig.clothingstore.fragment.model.ProductDescriptionFragmentPresenterInterface;
import com.andreyjig.clothingstore.fragment.views.ProductDescriptionFragmentView;
import com.andreyjig.clothingstore.model.Product;
import com.andreyjig.clothingstore.model.product.Color;
import com.andreyjig.clothingstore.model.product.Image;
import com.andreyjig.clothingstore.model.product.Size;
import com.andreyjig.clothingstore.model.product.Variant;
import com.andreyjig.clothingstore.utils.NetworkService;
import com.andreyjig.clothingstore.utils.ProductHelper;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

@InjectViewState
public class ProductDescriptionFragmentPresenter extends MvpPresenter<ProductDescriptionFragmentView>
        implements ProductDescriptionFragmentPresenterInterface {

    private final int NO_IMAGE = -2;
    private Product product;
    private Variant variant;
    private ArrayList<Color> colors;
    private ArrayList<Size> sizes;
    private ArrayList<Image> images;
    private int productId;
    private int variantId;
    private int colorId;
    private int sizeId;
    private int imageId;
    private boolean firstAttachView;
    private int newAttachView;

    public ProductDescriptionFragmentPresenter(ProductDescriptionFragmentArgs args) {
        productId = args.getProductId();
        variantId = args.getVariantId();
        String title = args.getName();
        getViewState().setTitle(title);
        getViewState().progressBarVisibility(View.VISIBLE);
        getProduct();
    }

    @Override
    public void attachView(ProductDescriptionFragmentView view) {
        super.attachView(view);
        newAttachView = 2;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        firstAttachView = true;
    }

    public void getProduct() {
        NetworkService.getInstance().getProduct(this, productId);
    }

    @Override
    public void setErrorDialog() {
        getViewState().getDialogError(v -> getProduct());
    }

    @Override
    public void setProduct(Product product) {
        getViewState().progressBarVisibility(View.INVISIBLE);
        this.product = product;
        getViewState().setProduct(product);
        variant = ProductHelper.getVariant(product, variantId);
        colorId = variant.getColorId();
        sizeId = variant.getSizeId();
        getColors();
    }

    public void getColors() {
        colors = ProductHelper.getAllColor(product);
        getViewState().setColorAdapter(colors);
    }

    public void setColor(int index) {
        if (firstAttachView){
            firstAttachView = false;
            newAttachView = 0;
        } else if (newAttachView > 1){
            newAttachView --;
            ArrayList<Integer> colorsId = ProductHelper.getColorsId(colors);
            getViewState().setColor(colorsId.indexOf(colorId));
            return;
        }
        colorId = colors.get(index).getId();
        String color = colors.get(index).getHashCode();
        getViewState().setColorDrawer(color);
        getSize();
    }

    public void getSize() {
        sizes = ProductHelper.getAllSizes(product, colorId);
        getViewState().setSizeAdapter(sizes);
        ArrayList<Integer> sizesId = ProductHelper.getSizesId(sizes);
        int index;
        if (sizesId.contains(sizeId)) {
            index = sizesId.indexOf(sizeId);
        } else {
            index = 0;
        }
        getViewState().setSize(index);
    }

    public void setSize(int index) {
        sizeId = sizes.get(index).getId();
        setVariant();
    }

    private void setVariant() {
        variant = ProductHelper.getVariant(product, colorId, sizeId);
        if (!variant.getName().isEmpty()) {
            getViewState().setName(variant.getName());
        } else {
            getViewState().setName(product.getName());
        }
        if (newAttachView > 0){
            newAttachView --;
        } else {
            images = variant.getPhotos();
            if (images != null && images.size() > 0) {
                imageId = 0;
            } else {
                imageId = NO_IMAGE;
            }
        }
        setImage(0);
    }

    public void setImage(int i) {
        if (imageId != NO_IMAGE) {
            getViewState().imageButtonVisibility(View.VISIBLE);
            imageId = (imageId + i) % images.size();
            if (imageId == -1) {
                imageId = images.size() - 1;
            }
            String imageUrl = images.get(imageId).getBig();
            getViewState().setImage(imageUrl);
        } else {
            getViewState().imageButtonVisibility(View.INVISIBLE);
            getViewState().setDefaultImage();
        }
    }
}
