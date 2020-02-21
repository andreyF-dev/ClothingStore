package com.andreyjig.clothingstore.ui.fragment;

import android.view.ViewGroup;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.presenter.BaseHandlerPresenter;
import com.andreyjig.clothingstore.util.CustomSnackBarError;
import com.andreyjig.clothingstore.ui.view.BaseHandlerView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public abstract class BaseHandlerFragment extends MvpAppCompatFragment
        implements BaseHandlerView{

    @InjectPresenter
    BaseHandlerPresenter baseHandlerPresenter;

    private CustomSnackBarError snackbar;

    @Override
    public void onDetach() {
        super.onDetach();
        hideErrorDialog();
    }

    @Override
    public void setShowErrorDialog(int errorMessageId) {
        baseHandlerPresenter.setErrorDialog(errorMessageId);
    }

    @Override
    public void showErrorDialog(int errorMessageId) {
        if (getView() != null) {
            String text = getString(errorMessageId);
            snackbar = CustomSnackBarError.make((ViewGroup) getView().getParent().getParent(),
                    CustomSnackBarError.LENGTH_INDEFINITE);
            snackbar.setText(text);
            snackbar.setAction(getString(R.string.download_now), v -> {
                errorDialogOnClick();
                setHideErrorDialog();
            });
            snackbar.show();
        }
    }

    @Override
    public void setHideErrorDialog() {
        baseHandlerPresenter.setHideErrorDialog();
    }

    @Override
    public void hideErrorDialog() {
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }

    @Override
    public void setTitle(int id) {
        baseHandlerPresenter.setTitle(getString(id));
    }

    @Override
    public void setTitle(String title) {
        baseHandlerPresenter.setTitle(title);
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() != null){
            getActivity().setTitle(title);
        }
    }

    public abstract void errorDialogOnClick();
}
