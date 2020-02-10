package com.andreyjig.clothingstore.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.andreyjig.clothingstore.R;

public class PlaceOrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private CardView cardView;

    public PlaceOrderHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.item_place_order_card);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
