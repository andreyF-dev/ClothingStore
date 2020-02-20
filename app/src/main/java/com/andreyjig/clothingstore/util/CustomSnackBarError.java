package com.andreyjig.clothingstore.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.andreyjig.clothingstore.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;

public class CustomSnackBarError extends BaseTransientBottomBar<CustomSnackBarError> {
    protected CustomSnackBarError(@NonNull ViewGroup parent, @NonNull View content, @NonNull com.google.android.material.snackbar.ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    public static CustomSnackBarError make(View view, @Duration int duration) {
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        ViewGroup parent = (ViewGroup)view.getParent();
        View content = inflater.inflate(R.layout.snackbar_error_view, parent, false);
        ContentViewCallback callback = new ContentViewCallback(content);
        CustomSnackBarError customSnackBarError = new CustomSnackBarError(parent, content, callback);
        customSnackBarError.setDuration(duration);
        return customSnackBarError;
    }

    public CustomSnackBarError setText(CharSequence text) {
        TextView textView = (TextView) getView().findViewById(R.id.snackbar_error_message);
        textView.setText(text);
        return this;
    }

    public CustomSnackBarError setAction(CharSequence text, final View.OnClickListener listener) {
        ImageView actionView = (ImageView) getView().findViewById(R.id.snackbar_action_button);
        actionView.setOnClickListener(view -> {
            listener.onClick(view);
            dismiss();
        });
        return this;
    }

    private static class ContentViewCallback implements com.google.android.material.snackbar.ContentViewCallback {

        private View content;

        public ContentViewCallback(View content) {
            this.content = content;
        }

        @Override
        public void animateContentIn(int delay, int duration) {
            ViewCompat.setScaleY(content, 0f);
            ViewCompat.animate(content)
                    .scaleY(1f).setDuration(duration)
                    .setStartDelay(delay);
        }

        @Override
        public void animateContentOut(int delay, int duration) {
            // add custom *out animations for your views
            // e.g. original snackbar uses alpha animation, from 1 to 0
            ViewCompat.setScaleY(content, 1f);
            ViewCompat.animate(content)
                    .scaleY(0f)
                    .setDuration(duration)
                    .setStartDelay(delay);
        }
    }
}
