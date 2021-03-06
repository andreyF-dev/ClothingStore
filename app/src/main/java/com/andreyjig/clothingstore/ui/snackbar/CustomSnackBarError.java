package com.andreyjig.clothingstore.ui.snackbar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.andreyjig.clothingstore.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;

public class CustomSnackBarError extends BaseTransientBottomBar<CustomSnackBarError> {
    protected CustomSnackBarError(@NonNull ViewGroup parent, @NonNull View content, @NonNull com.google.android.material.snackbar.ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    public static CustomSnackBarError make(ViewGroup parent, @Duration int duration) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View content = inflater.inflate(R.layout.snackbar_error_view, null);
        ContentViewCallback callback = new ContentViewCallback(content);
        CustomSnackBarError customSnackBarError = new CustomSnackBarError(parent, content, callback);
        customSnackBarError.setDuration(duration);
        View view = customSnackBarError.getView();
        view.setPadding(0,0,0,0);
        int color = parent.getContext().getResources().getColor(android.R.color.transparent);
        view.setBackgroundColor(color);
        return customSnackBarError;
    }


    public CustomSnackBarError setText(CharSequence text) {
        TextView textView = getView().findViewById(R.id.snackbar_error_message);
        textView.setText(text);
        return this;
    }

    public CustomSnackBarError setAction(final View.OnClickListener listener) {
        ImageView actionView = getView().findViewById(R.id.snackbar_action_button);
        actionView.setOnClickListener(view -> {
            listener.onClick(view);
            dismiss();
        });
        return this;
    }

    private static class ContentViewCallback implements com.google.android.material.snackbar.ContentViewCallback {

        private View content;

        ContentViewCallback(View content) {
            this.content = content;
        }

        @Override
        public void animateContentIn(int delay, int duration) {

            ObjectAnimator animationX = ObjectAnimator.ofFloat(content, View.SCALE_X, 0F, 1F);
            AnimatorSet set = new AnimatorSet();
            set.play(animationX);
            set.setDuration(duration);
            set.setInterpolator(new DecelerateInterpolator());
            set.start();
        }

        @Override
        public void animateContentOut(int delay, int duration) {
            ObjectAnimator animationX = ObjectAnimator.ofFloat(content, "scaleX", 0F);
            AnimatorSet set = new AnimatorSet();
            set.play(animationX);
            set.setDuration(duration);
            set.setInterpolator(new DecelerateInterpolator());
            set.start();
        }
    }
}
