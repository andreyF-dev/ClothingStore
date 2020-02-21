package com.andreyjig.clothingstore.ui.view;

import com.andreyjig.clothingstore.entity.Product;
import com.andreyjig.clothingstore.entity.product.Color;
import com.andreyjig.clothingstore.entity.product.Image;
import com.andreyjig.clothingstore.entity.product.Size;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.ArrayList;

public interface ProductDescriptionView extends MvpView, BaseHandlerView {

    @StateStrategyType(AddToEndStrategy.class)
    void updateProduct(Product product);
    @StateStrategyType(SkipStrategy.class)
    void updateCurrentColor(int index);
    @StateStrategyType(SkipStrategy.class)
    void updateCurrentSize(int index);
    @StateStrategyType(SkipStrategy.class)
    void updateVariantName(String string);
    @StateStrategyType(SkipStrategy.class)
    void updateImages(ArrayList<Image> images);
    @StateStrategyType(SkipStrategy.class)
    void updateColors(ArrayList<Color> colors);
    @StateStrategyType(SkipStrategy.class)
    void updateSizes(ArrayList<Size> sizes);
    @StateStrategyType(AddToEndStrategy.class)
    void showProgressBar();
    @StateStrategyType(SingleStateStrategy.class)
    void hideProgressBar();
    @StateStrategyType(SkipStrategy.class)
    void updateColorDrawer(String color);

}
