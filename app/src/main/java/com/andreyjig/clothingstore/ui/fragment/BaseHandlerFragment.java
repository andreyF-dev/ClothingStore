package com.andreyjig.clothingstore.ui.fragment;

import android.view.ViewGroup;
import com.andreyjig.clothingstore.ui.snackbar.CustomSnackBarError;
import com.andreyjig.clothingstore.mvp.view.BaseHandlerView;
import com.arellomobile.mvp.MvpAppCompatFragment;

public abstract class BaseHandlerFragment extends MvpAppCompatFragment
        implements BaseHandlerView{

    private CustomSnackBarError snackbar;

    @Override
    public void onDetach() {
        super.onDetach();
        hideErrorDialog();
    }

    @Override
    public void showErrorDialog(int errorMessageId) {
        if (getView() != null) {
            String text = getString(errorMessageId);
            snackbar = CustomSnackBarError.make((ViewGroup)getView(),
                    CustomSnackBarError.LENGTH_INDEFINITE);
            snackbar.setText(text);
            snackbar.setAction(v -> onErrorDialogClick());
            snackbar.show();
        }
    }

    protected abstract void onErrorDialogClick();

    @Override
    public void hideErrorDialog() {
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }

    @Override
    public void updateTitle(int id) {
        updateTitle(getString(id));
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() != null){
            getActivity().setTitle(title);
        }
    }
}
