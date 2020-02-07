package com.andreyjig.clothingstore.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.andreyjig.clothingstore.adapter.CartAdapter;
import com.andreyjig.clothingstore.model.product.Color;
import com.andreyjig.clothingstore.model.product.Image;
import com.andreyjig.clothingstore.model.product.Properties;
import com.andreyjig.clothingstore.model.product.Size;
import com.andreyjig.clothingstore.model.product.Variant;
import com.andreyjig.clothingstore.utils.NetworkService;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.model.Product;
import com.andreyjig.clothingstore.model.shell.ProductShell;
import com.andreyjig.clothingstore.utils.ProductHelper;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {

    private static String ARG_ID = "arg_id_product";

    private View.OnClickListener snackBarOnClickListener;

    private Integer id;
    private Product product;
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

    public ProductFragment() {

    }

    public static ProductFragment newInstance(int id){
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            id = getArguments().getInt(ARG_ID);
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

        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentColorId = colors.get(position).getId();
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

        snackBarOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProduct();
            }
        };

        if (product == null){
            getProduct();
        }
        return view;
    }

    private void getProduct() {
        NetworkService.newInstance()
                .getJSONApi()
                .getProduct(id)
                .enqueue(new Callback<ProductShell>() {
                    @Override
                    public void onResponse(Call<ProductShell> call, Response<ProductShell> response) {
                        if (response.isSuccessful()) {
                            product = response.body().getProduct();
                            if (getContext() != null) {
                                setProduct();
                            }
                        } else {
                            errorLoading();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductShell> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private void errorLoading() {
        Snackbar.make(imageView, getString(R.string.error_download), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.download_now, snackBarOnClickListener)
                .show();
    }

    private void setProduct() {
        textViewDescription.setText(product.getDescription());
        textViewManufacturer.setText(product.getManufacturer().getName());
        textViewMaterial.setText(product.getMaterial().getName());

        getActivity().setTitle(product.getName());
        colors = ProductHelper.getAllColor(product);
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item, ProductHelper.getColorString(colors));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapter);
    }

    private void setSizeAdapter(){
        sizes = ProductHelper.getSizes(product, currentColorId);
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item, ProductHelper.getSizesString(sizes));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter);
        ArrayList<Integer> numbers = ProductHelper.getSizesId(sizes);
        if (numbers.contains(currentSizeId)){
            spinnerSize.setSelection(numbers.indexOf(currentSizeId));
        } else {
            spinnerSize.setSelection(1);
        }
    }

    private void setVariant (){
        Variant variant = ProductHelper.getVariant(product, currentColorId, currentSizeId);
        if (variant != null && !variant.getName().isEmpty()){
            textViewName.setText(variant.getName());
        } else {
            textViewName.setText(product.getName());
        }
        images = variant.getPhotos();
        imageId = 0;
        setImage(imageId);
    }

    private void setImage(int i) {
        String imageUrl = images.get(i).getBig();
        Picasso.get()
                .load(imageUrl)
                .into(imageView);
    }


}
