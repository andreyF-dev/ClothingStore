package com.andreyjig.clothingstore.utils;

import android.content.Context;
import android.view.View;

import com.andreyjig.clothingstore.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarHelper {

    public static Snackbar showSnackbar(Context context, View view, View.OnClickListener listener){
        return Snackbar.make(view, context.getString(R.string.error_download), Snackbar.LENGTH_INDEFINITE)
                .setAction(context.getString(R.string.download_now), listener);
    }
}
