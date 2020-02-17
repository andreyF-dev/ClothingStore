package com.andreyjig.clothingstore.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.andreyjig.clothingstore.adapter.SpinnerPropertiesAdapter;
import com.andreyjig.clothingstore.fragment.presenters.ProductDescriptionPresenter;
import com.andreyjig.clothingstore.fragment.views.ProductDescriptionView;
import com.andreyjig.clothingstore.activity.views.TitleHandlerView;
import com.andreyjig.clothingstore.model.product.Color;
import com.andreyjig.clothingstore.model.product.Size;
import com.andreyjig.clothingstore.utils.ColorDrawer;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.model.Product;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ProductDescriptionFragment extends FragmentWithErrorHandler implements
        ProductDescriptionView {


    @InjectPresenter
    ProductDescriptionPresenter presenter;

    @ProvidePresenter
    ProductDescriptionPresenter providePresenter (){
        return new ProductDescriptionPresenter(ProductDescriptionFragmentArgs
                .fromBundle(getArguments()), this, (TitleHandlerView)getContext());
    }

    private ImageView imageView;
    private TextView textViewName;
    private Spinner spinnerColor;
    private Spinner spinnerSize;
    private TextView textViewManufacturer;
    private TextView textViewDescription;
    private TextView textViewMaterial;
    private FrameLayout frameLayout;
    private ImageButton imageForward;
    private ImageButton imageBack;
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
        imageView = view.findViewById(R.id.fragment_product_image);
        textViewName = view.findViewById(R.id.fragment_product_name);
        spinnerColor = view.findViewById(R.id.fragment_product_color_spinner);
        spinnerSize = view.findViewById(R.id.fragment_product_size_spinner);
        textViewManufacturer = view.findViewById(R.id.fragment_product_text_manufacturer);
        textViewDescription = view.findViewById(R.id.fragment_product_text_description);
        textViewMaterial = view.findViewById(R.id.fragment_product_text_material);
        frameLayout = view.findViewById(R.id.fragment_product_color_layout);
        imageForward = view.findViewById(R.id.fragment_product_image_forward);
        imageBack = view.findViewById(R.id.fragment_product_image_back);
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
        imageForward.setOnClickListener(v -> presenter.setImage(1));
        imageBack.setOnClickListener(v -> presenter.setImage(-1));
    }

    @Override
    public void updateProduct(Product product) {
        textViewDescription.setText(product.getDescription());
        textViewManufacturer.setText(product.getManufacturer().getName());
        textViewMaterial.setText(product.getMaterial().getName());
    }

    @Override
    public void updateCurrentColor(int index) {
        Log.d("Retrofit", "indexColor = " + index);
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
    public void updateImage(String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .noPlaceholder()
                .into(imageView);
    }

    @Override
    public void updateColors(ArrayList<Color> colors) {
        SpinnerPropertiesAdapter adapter =
                new SpinnerPropertiesAdapter(getContext(), new ArrayList<>(colors));
        spinnerColor.setAdapter(adapter);
    }

    @Override
    public void updateSizes(ArrayList<Size> sizes) {
        SpinnerPropertiesAdapter adapter =
                new SpinnerPropertiesAdapter(getContext(), new ArrayList<>(sizes));
        spinnerSize.setAdapter(adapter);
    }

    @Override
    public void progressBarVisibility() {
        progressBarState(View.VISIBLE);
    }

    @Override
    public void progressBarInvisibility() {
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
    public void imageButtonVisibility() {
        imageButtonState(View.VISIBLE);
    }

    @Override
    public void imageButtonInvisibility() {
        imageButtonState(View.INVISIBLE);
    }

    private void imageButtonState(int state){
        imageBack.setVisibility(state);
        imageForward.setVisibility(state);
    }

    @Override
    public void setDefaultImage() {
        imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_photo));
    }
}
