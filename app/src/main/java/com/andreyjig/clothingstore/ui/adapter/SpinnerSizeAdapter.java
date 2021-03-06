package com.andreyjig.clothingstore.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.andreyjig.clothingstore.entity.product.Size;
import java.util.ArrayList;

public class SpinnerSizeAdapter extends ArrayAdapter<Size> {

    private Context context;

    public SpinnerSizeAdapter(@NonNull Context context, @NonNull ArrayList<Size> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getItemView(position, convertView, parent);
    }

    private View getItemView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Size size = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(size.getName());
        return convertView;
    }


}
