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
    void setProduct (Product product);
    @StateStrategyType(SkipStrategy.class)
    void setColor(int index);
    @StateStrategyType(SkipStrategy.class)
    void setSize(int index);
    @StateStrategyType(SkipStrategy.class)
    void setName(String string);
    @StateStrategyType(SkipStrategy.class)
    void setImage(String imageUrl);
    @StateStrategyType(SkipStrategy.class)
    void setColors(ArrayList<Color> colors);
    @StateStrategyType(SkipStrategy.class)
    void setSizes(ArrayList<Size> sizes);
    @StateStrategyType(AddToEndStrategy.class)
    void progressBarVisibility();
    @StateStrategyType(SingleStateStrategy.class)
    void progressBarInvisibility();
    @StateStrategyType(SkipStrategy.class)
    void setColorDrawer(String color);
    @StateStrategyType(SkipStrategy.class)
    void imageButtonVisibility(int state);
    @StateStrategyType(SkipStrategy.class)
    void setDefaultImage();
}
