package com.andreyjig.clothingstore.presenters;

import com.andreyjig.clothingstore.fragment.ProductDescriptionFragmentArgs;
import com.andreyjig.clothingstore.model.handler.ProductDescription;
import com.andreyjig.clothingstore.views.ProductDescriptionView;
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
public class ProductDescriptionPresenter extends MvpPresenter<ProductDescriptionView> {

    private final int NO_IMAGE = -2;
    private final int NO_STEP = 0;
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

    public ProductDescriptionPresenter(ProductDescriptionFragmentArgs args) {
        productId = args.getProductId();
        variantId = args.getVariantId();
        title = args.getName();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setTitle(title);
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
        ProductDescription productDescription = new ProductDescription() {
            @Override
            public void setDownloadedProduct(Product product) {
                setProduct(product);
            }

            @Override
            public void setErrorDownloaded(String errorText) {
                setErrorDialog(errorText);
            }
        };
        NetworkService.getInstance().getProduct(productDescription, productId);
    }

    private void setErrorDialog(String errorText){
        getViewState().setShowErrorDialog(errorText);
    }

    public void errorDialogOnClick(){
        getProduct();
    }

    public void setProduct(Product product) {
        if (product != null) {
            getViewState().progressBarInvisibility();
            this.product = product;
            variant = ProductHelper.getVariant(product, variantId);
            colorId = variant.getColorId();
            sizeId = variant.getSizeId();
            setProductDescription();
            getColors();
        } else {
            setErrorDialog("Product object is null");
        }
    }

    private void setProductDescription(){
        getViewState().updateProduct(product);
    }

    private void getColors() {
        colors = ProductHelper.getAllColor(product);
        int indexCurrentColor = ProductHelper.getIndexById(new ArrayList<>(colors), colorId);
        getViewState().updateColors(colors);
        setBeginColor(indexCurrentColor);
    }

    private void setBeginColor(int index){
        if (index != 0) {
            getViewState().updateCurrentColor(index);
        }
    }

    public void setColor(int index) {
        colorId = colors.get(index).getId();
        setColorDrawer(index);
    }

    private void setColorDrawer(int index){
        String color = colors.get(index).getHashCode();
        getViewState().updateColorDrawer(color);
        getSize();
    }

    private void getSize() {
        sizes = ProductHelper.getAllSizes(product, colorId);
        int indexCurrentSize = ProductHelper.getIndexById(new ArrayList<>(sizes), sizeId);
        getViewState().updateSizes(sizes);
        setBeginSize(indexCurrentSize);
    }

    private void setBeginSize(int index){
        if (index != 0) {
            getViewState().updateCurrentSize(index);
        }
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
            getViewState().updateVariantName(variant.getName());
        } else {
            getViewState().updateVariantName(product.getName());
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
        setImage(NO_STEP);
    }

    public void setImage(int step) {
        if (imageIndex != NO_IMAGE) {
            getViewState().imageButtonVisibility();
            imageIndex = (imageIndex + step) % images.size();
            if (imageIndex == -1) {
                imageIndex = images.size() - 1;
            }
            String imageUrl = images.get(imageIndex).getBig();
            getViewState().updateImage(imageUrl);
        } else {
            getViewState().imageButtonInvisibility();
            getViewState().setDefaultImage();
        }
    }
}
