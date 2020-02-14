package com.andreyjig.clothingstore.fragment.presenters;

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

    public ProductDescriptionFragmentPresenter(ProductDescriptionFragmentArgs args) {
        productId = args.getProductId();
        variantId = args.getVariantId();
        String title = args.getName();
        getViewState().setTitle(title);
        getViewState().progressBarVisibility(View.VISIBLE);
        getProduct();
    }


    public void getProduct() {
        NetworkService.getInstance().getProduct(this, productId);
    }

    @Override
    public void setErrorDialog() {
        getViewState().getDialogError();
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
        ArrayList<Integer> colorsId = ProductHelper.getColorsId(colors);
        int index;
        if (colorsId.contains(colorId)) {
            index = colorsId.indexOf(colorId);
        } else {
            index = 0;
        }
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
        if (variant != null) {
            if (!variant.getName().isEmpty()) {
                getViewState().setName(variant.getName());
            } else {
                getViewState().setName(product.getName());
            }
            images = variant.getPhotos();
            if (images != null && images.size() > 0) {
                imageId = 0;
                getViewState().imageButtonVisibility(View.VISIBLE);
                setImage(0);
            } else {
                getViewState().imageButtonVisibility(View.INVISIBLE);
                getViewState().setDefaultImage();
            }
        }
    }

    public void setImage(int i) {
        imageId = (imageId + i) % images.size();
        if (imageId == -1) {
            imageId = images.size() - 1;
        }
        String imageUrl = images.get(imageId).getBig();
        getViewState().setImage(imageUrl);
    }

}
