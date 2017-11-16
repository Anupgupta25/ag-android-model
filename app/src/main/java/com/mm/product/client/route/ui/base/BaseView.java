package com.mm.product.client.route.ui.base;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public interface BaseView {

    void showLoading(String message);

    void showLoading();

    void hideLoading();

    void onUnknownError(String error);

    void onTimeout();

    void onNetworkError();

    boolean isNetworkConnected();

    void onConnectionError();

    void hideKeyboard();
}
