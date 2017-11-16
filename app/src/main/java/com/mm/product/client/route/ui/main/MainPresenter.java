package com.mm.product.client.route.ui.main;

import android.support.annotation.NonNull;

import com.mm.product.client.route.data.model.RouteLists;
import com.mm.product.client.route.ui.base.BasePresenter;
import com.mm.product.client.route.ui.base.BaseView;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public interface MainPresenter<V extends BaseView> extends BasePresenter<V> {

    void getRoutes();

    void getRoutesDetails(@NonNull long id, RouteLists routeLists);
}
