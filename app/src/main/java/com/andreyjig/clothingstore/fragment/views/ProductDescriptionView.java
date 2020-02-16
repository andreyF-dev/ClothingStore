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

@StateStrategyType(SkipStrategy.class)
public interface ProductDescriptionView extends MvpView {

    void setProduct (Product product);
    void setColor(int index);
    void setSize(int index);
    void setName(String string);
    void setImage(String imageUrl);
    void setColors(ArrayList<Color> colors);
    void setSizes(ArrayList<Size> sizes);
    void progressBarVisibility();
    void progressBarInvisibility();
    void setColorDrawer(String color);
    void imageButtonVisibility(int state);
    void setDefaultImage();
}
