package com.andreyjig.clothingstore.ui.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.andreyjig.clothingstore.entity.product.Image;
import com.andreyjig.clothingstore.ui.adapter.ProductDescriptionImageAdapter;
import com.andreyjig.clothingstore.ui.adapter.SpinnerColorAdapter;
import com.andreyjig.clothingstore.ui.adapter.SpinnerSizeAdapter;
import com.andreyjig.clothingstore.presenter.ProductDescriptionPresenter;
import com.andreyjig.clothingstore.ui.view.ProductDescriptionView;
import com.andreyjig.clothingstore.entity.product.Color;
import com.andreyjig.clothingstore.entity.product.Size;
import com.andreyjig.clothingstore.ui.view.ColorDrawer;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.Product;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import java.util.ArrayList;

public class ProductDescriptionFragment extends BaseHandlerFragment implements
        ProductDescriptionView {


    @InjectPresenter
    ProductDescriptionPresenter presenter;

    @ProvidePresenter
    ProductDescriptionPresenter providePresenter (){
        return new ProductDescriptionPresenter(ProductDescriptionFragmentArgs
                .fromBundle(getArguments()));
    }

    private ViewPager2 imagePager;
    private TextView textViewName;
    private Spinner spinnerColor;
    private Spinner spinnerSize;
    private TextView textViewManufacturer;
    private TextView textViewDescription;
    private TextView textViewMaterial;
    private FrameLayout frameLayout;
    private ProgressBar progressBarImage;
    private ProgressBar progressBarManufacturer;
    private ProgressBar progressBarDescription;
    private ProgressBar progressBarMaterial;
    private ProgressBar progressBarName;

    public ProductDescriptionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imagePager = view.findViewById(R.id.fragment_product_view_pager_image);
        textViewName = view.findViewById(R.id.fragment_product_name);
        spinnerColor = view.findViewById(R.id.fragment_product_color_spinner);
        spinnerSize = view.findViewById(R.id.fragment_product_size_spinner);
        textViewManufacturer = view.findViewById(R.id.fragment_product_text_manufacturer);
        textViewDescription = view.findViewById(R.id.fragment_product_text_description);
        textViewMaterial = view.findViewById(R.id.fragment_product_text_material);
        frameLayout = view.findViewById(R.id.fragment_product_color_layout);
        progressBarImage = view.findViewById(R.id.fragment_product_progress_bar_image);
        progressBarManufacturer = view.findViewById(R.id.fragment_product_progress_bar_text_manufacturer);
        progressBarDescription = view.findViewById(R.id.fragment_product_progress_bar_text_description);
        progressBarMaterial = view.findViewById(R.id.fragment_product_progress_bar_text_material);
        progressBarName = view.findViewById(R.id.fragment_product_progress_bar_name);
        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setColor(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setSize(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        imagePager.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.HORIZONTAL));
        imagePager.setOffscreenPageLimit(1);
    }

    @Override
    public void updateProduct(Product product) {
        textViewDescription.setText(product.getDescription());
        textViewManufacturer.setText(product.getManufacturer().getName());
        textViewMaterial.setText(product.getMaterial().getName());
    }

    @Override
    public void updateCurrentColor(int index) {
        spinnerColor.setSelection(index);
    }

    @Override
    public void updateCurrentSize(int index) {
        spinnerSize.setSelection(index);
    }

    @Override
    public void updateVariantName(String string) {
        textViewName.setText(string);
    }

    @Override
    public void updateImages(ArrayList<Image> images) {
        ProductDescriptionImageAdapter adapter =
                new ProductDescriptionImageAdapter(getContext(), images);
        imagePager.setAdapter(adapter);
    }

    @Override
    public void updateColors(ArrayList<Color> colors) {
        SpinnerColorAdapter adapter =
                new SpinnerColorAdapter(getContext(), colors);
        spinnerColor.setAdapter(adapter);
    }

    @Override
    public void updateSizes(ArrayList<Size> sizes) {
        SpinnerSizeAdapter adapter =
                new SpinnerSizeAdapter(getContext(), sizes);
        spinnerSize.setAdapter(adapter);
    }

    @Override
    public void showProgressBar() {
        progressBarState(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarState(View.INVISIBLE);
    }

    private void progressBarState(int state) {
        progressBarImage.setVisibility(state);
        progressBarManufacturer.setVisibility(state);
        progressBarDescription.setVisibility(state);
        progressBarMaterial.setVisibility(state);
        progressBarName.setVisibility(state);
    }

    @Override
    public void updateColorDrawer(String color) {
        frameLayout.addView(new ColorDrawer(getContext(), color));
    }

    @Override
    public void errorDialogOnClick() {
        presenter.errorDialogOnClick();
    }
}
