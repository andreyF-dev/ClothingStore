package com.andreyjig.clothingstore.fragment.views;

import com.andreyjig.clothingstore.model.Product;
import com.andreyjig.clothingstore.model.product.Color;
import com.andreyjig.clothingstore.model.product.Size;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.ArrayList;

public interface ProductDescriptionView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void updateProduct(Product product);
    @StateStrategyType(SkipStrategy.class)
    void updateCurrentColor(int index);
    @StateStrategyType(SkipStrategy.class)
    void updateCurrentSize(int index);
    @StateStrategyType(SkipStrategy.class)
    void updateVariantName(String string);
    @StateStrategyType(SkipStrategy.class)
    void updateImage(String imageUrl);
    @StateStrategyType(SkipStrategy.class)
    void updateColors(ArrayList<Color> colors);
    @StateStrategyType(SkipStrategy.class)
    void updateSizes(ArrayList<Size> sizes);
    @StateStrategyType(AddToEndStrategy.class)
    void progressBarVisibility();
    @StateStrategyType(SingleStateStrategy.class)
    void progressBarInvisibility();
    @StateStrategyType(SkipStrategy.class)
    void updateColorDrawer(String color);
    @StateStrategyType(SkipStrategy.class)
    void imageButtonVisibility();
    @StateStrategyType(SkipStrategy.class)
    void imageButtonInvisibility();
    @StateStrategyType(SkipStrategy.class)
    void setDefaultImage();
}
