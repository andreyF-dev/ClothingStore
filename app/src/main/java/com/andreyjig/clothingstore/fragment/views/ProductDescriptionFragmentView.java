package com.andreyjig.clothingstore.fragment.views;

import android.view.View;

import com.andreyjig.clothingstore.model.Product;
import com.andreyjig.clothingstore.model.product.Color;
import com.andreyjig.clothingstore.model.product.Size;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;


public interface ProductDescriptionFragmentView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void setTitle (String string);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setProduct (Product product);

    @StateStrategyType(SkipStrategy.class)
    void setColor(int index);

    @StateStrategyType(SkipStrategy.class)
    void setSize(int index);

    @StateStrategyType(SkipStrategy.class)
    void setName(String string);

    @StateStrategyType(SkipStrategy.class)
    void setImage(String imageUrl);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setColorAdapter(ArrayList<Color> colors);

    @StateStrategyType(SkipStrategy.class)
    void setSizeAdapter(ArrayList<Size> sizes);

    @StateStrategyType(SkipStrategy.class)
    void progressBarVisibility(int state);

    @StateStrategyType(SkipStrategy.class)
    void setColorDrawer(String color);

    @StateStrategyType(SkipStrategy.class)
    void imageButtonVisibility(int state);

    @StateStrategyType(SkipStrategy.class)
    void setDefaultImage();
}
