package com.andreyjig.clothingstore.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.ui.adapter.model.TotalPrice;

public class PriceHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private Context context;

    public PriceHolder(@NonNull View itemView, Context context) {
        super(itemView);
        textView = itemView.findViewById(R.id.item_price_text);
        this.context = context;
    }

    public void bind(TotalPrice price){
        String text = String.format(context.getString(R.string.total_price), price.getSum());
        textView.setText(text);
    }
}
