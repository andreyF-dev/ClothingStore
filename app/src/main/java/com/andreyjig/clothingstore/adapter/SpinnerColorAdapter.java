package com.andreyjig.clothingstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.andreyjig.clothingstore.model.product.Color;

import java.util.ArrayList;

public class SpinnerColorAdapter extends ArrayAdapter<Color> {

    private Context context;

    public SpinnerColorAdapter(@NonNull Context context, @NonNull ArrayList<Color> objects) {
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
                .setText(getItem(position).getName());
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}
