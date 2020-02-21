package com.andreyjig.clothingstore.ui.adapter.holder;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceOrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public PlaceOrderHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}
