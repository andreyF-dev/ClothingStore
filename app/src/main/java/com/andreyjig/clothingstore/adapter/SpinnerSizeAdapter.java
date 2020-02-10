package com.andreyjig.clothingstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.andreyjig.clothingstore.model.product.Size;

import java.util.ArrayList;
import java.util.Objects;

public class SpinnerSizeAdapter extends ArrayAdapter<Size> {

    private Context context;

    public SpinnerSizeAdapter(@NonNull Context context, @NonNull ArrayList<Size> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        ((TextView) v.findViewById(android.R.id.text1))
                .setText(Objects.requireNonNull(getItem(position)).getName());
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}
