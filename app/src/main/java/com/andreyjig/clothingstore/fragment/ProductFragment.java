package com.andreyjig.clothingstore.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.andreyjig.clothingstore.model.product.Color;
import com.andreyjig.clothingstore.model.product.Image;
import com.andreyjig.clothingstore.model.product.Size;
import com.andreyjig.clothingstore.model.product.Variant;
import com.andreyjig.clothingstore.utils.ColorDrawer;
import com.andreyjig.clothingstore.utils.NetworkService;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.model.Product;
import com.andreyjig.clothingstore.model.shell.ProductShell;
import com.andreyjig.clothingstore.utils.ProductHelper;
import com.andreyjig.clothingstore.utils.SnackBarHelper;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {

    private static String ARG_ID_PRODUCT = "arg_id_product";
    private static String ARG_ID_VARIANT = "arg_id_variant";

    private Snackbar snackbar;
    private View.OnClickListener snackBarOnClickListener;

    private int productId;
    private int variantId;
    private Product product;
    private Variant currentVariant;
    private ArrayList<Color> colors;
    private ArrayList<Size> sizes;
    private ArrayList<Image> images;
    private int currentColorId;
    private int currentSizeId;
    private int imageId;

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

    public ProductFragment() {

    }

    public static ProductFragment newInstance(int productId, int variantId){
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_PRODUCT, productId);
        args.putInt(ARG_ID_VARIANT, variantId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            productId = getArguments().getInt(ARG_ID_PRODUCT);
            variantId = getArguments().getInt(ARG_ID_VARIANT);
        }
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home && getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);

        imageView = view.findViewById(R.id.fragment_product_image);
        textViewName= view.findViewById(R.id.fragment_product_name);
        spinnerColor= view.findViewById(R.id.fragment_product_color_spinner);
        spinnerSize= view.findViewById(R.id.fragment_product_size_spinner);
        textViewManufacturer= view.findViewById(R.id.fragment_product_text_manufacturer);
        textViewDescription= view.findViewById(R.id.fragment_product_text_description);
        textViewMaterial= view.findViewById(R.id.fragment_product_text_material);
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
                currentColorId = colors.get(position).getId();
                String color = colors.get(position).getHashCode();
                frameLayout.addView(new ColorDrawer(getContext(), color));
                setSizeAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSizeId = sizes.get(position).getId();
                setVariant();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        snackBarOnClickListener = v -> getProduct();
        imageForward.setOnClickListener(v -> setImage(1));
        imageBack.setOnClickListener(v -> setImage(-1));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (product == null){
            getProduct();
            setVisibilityProgressBar(View.VISIBLE);
        } else {
            setProduct();
        }
    }

    private void setVisibilityProgressBar(int code){

        progressBarImage.setVisibility(code);
        progressBarManufacturer.setVisibility(code);
        progressBarDescription.setVisibility(code);
        progressBarMaterial.setVisibility(code);
        progressBarName.setVisibility(code);

    }

    private void getProduct() {
        NetworkService.getInstance()
                .getJSONApi()
                .getProduct(productId)
                .enqueue(new Callback<ProductShell>() {
                    @Override
                    public void onResponse(Call<ProductShell> call, Response<ProductShell> response) {
                        if (response.isSuccessful()) {
                            product = response.body().getProduct();
                            if (getContext() != null) {
                                setVisibilityProgressBar(View.INVISIBLE);
                                setProduct();
                            }
                        } else {
                            errorLoading();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductShell> call, Throwable t) {
                        t.printStackTrace();
                        errorLoading();
                    }
                });
    }

    private void errorLoading() {

        if (getContext() != null) {
            snackbar = SnackBarHelper.showSnackbar(getContext(), imageView, snackBarOnClickListener);
            snackbar.show();
        }

    }

    private void setProduct() {

        textViewDescription.setText(product.getDescription());
        textViewManufacturer.setText(product.getManufacturer().getName());
        textViewMaterial.setText(product.getMaterial().getName());
        Objects.requireNonNull(getActivity()).setTitle(product.getName());
        currentVariant = ProductHelper.getVariant(product, variantId);
        setColor();

    }

    private void setColor() {

        colors = ProductHelper.getAllColor(product);
        SpinnerPropertiesAdapter adapter = new SpinnerPropertiesAdapter(getContext(), new ArrayList<>(colors));
        spinnerColor.setAdapter(adapter);
        ArrayList<Integer> colorsId = ProductHelper.getColorsId(colors);
        if (colorsId.contains(currentVariant.getColorId())){
            spinnerColor.setSelection(colorsId.indexOf(currentVariant.getColorId()));
        } else {
            spinnerColor.setSelection(1);
        }
    }

    private void setSizeAdapter(){
        sizes = ProductHelper.getSizes(product, currentColorId);
        SpinnerPropertiesAdapter adapter = new SpinnerPropertiesAdapter(getContext(), new ArrayList<>(sizes));;
        spinnerSize.setAdapter(adapter);
        ArrayList<Integer> numbers = ProductHelper.getSizesId(sizes);
        if (numbers.contains(currentVariant.getSizeId())){
            spinnerSize.setSelection(numbers.indexOf(currentVariant.getSizeId()));
        } else {
            spinnerSize.setSelection(1);
        }
    }

    private void setVariant (){
        currentVariant = ProductHelper.getVariant(product, currentColorId, currentSizeId);
        if (currentVariant != null && !currentVariant.getName().isEmpty()){
            if (!currentVariant.getName().isEmpty()){
                textViewName.setText(currentVariant.getName());
            } else {
                textViewName.setText(product.getName());
            }
            images = currentVariant.getPhotos();
            if (images != null && images.size() > 0) {
                imageId = 0;
                setImage(0);
                imageBack.setVisibility(View.VISIBLE);
                imageForward.setVisibility(View.VISIBLE);
            } else {
                imageBack.setVisibility(View.INVISIBLE);
                imageForward.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void setImage(int i) {
        imageId = (imageId + i) % images.size();
        if (imageId == -1){
            imageId = images.size() - 1;
        }
        String imageUrl = images.get(imageId).getBig();
        Picasso.get()
                .load(imageUrl)
                .noPlaceholder()
                .into(imageView);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }
}
