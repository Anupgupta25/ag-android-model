package com.mm.product.client.route.ui.base;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public interface BasePresenter<V extends BaseView> {

    void onAttach(V mvpView);

    void onDetach();
}
