package com.andreyjig.clothingstore.utils;

import android.content.Context;
import android.view.View;
import com.andreyjig.clothingstore.R;
import com.google.android.material.snackbar.Snackbar;

public class ErrorHandlingHelper {

    public static Snackbar showSnackBar(Context context, View view, View.OnClickListener listener){
        Snackbar snackbar = Snackbar.make(view, context.getString(R.string.error_download), Snackbar.LENGTH_INDEFINITE)
                .setAction(context.getString(R.string.download_now), listener);
        int color = context.getResources().getColor(R.color.colorPrimary);
        snackbar.setActionTextColor(color);
        return snackbar;
    }
}
