package com.mm.product.client.route.ui.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mm.product.client.route.data.model.RouteLists;
import com.mm.product.client.route.data.model.Routes;
import com.mm.product.client.route.data.network.CallbackWrapper;
import com.mm.product.client.route.data.network.api.ApiManager;
import com.mm.product.client.route.ui.base.BasePresenterImp;
import com.mm.product.client.route.utills.rx.schedulers.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public class MainPresenterImp<V extends MainView>
        extends BasePresenterImp<V> implements MainPresenter<V> {

    private static final String TAG = MainPresenterImp.class.getSimpleName();

    @Inject
    protected MainPresenterImp(ApiManager apiManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(apiManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getRoutes() {
        if(getView() != null) {
            if (getView().isNetworkConnected()) {
                getView().showLoading();
                Log.i(TAG, getApiManager().getRouteList().toString());
                getCompositeDisposable().add(getApiManager().getRouteList()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeWith(new CallbackWrapper<RouteLists>(getView()) {

                            @Override
                            protected void onSuccess(RouteLists routes) {
                                if (!isViewAttached()) {
                                    return;
                                }
                                getView().onRoutesLoaded(routes);
                                getView().hideLoading();
                            }
                        }));
            } else {
                getView().onConnectionError();
            }
        }
    }

    @Override
    public void getRoutesDetails(@NonNull long id, RouteLists routeLists) {
        if(getView() != null) {
            if (getView().isNetworkConnected()) {
                getView().showLoading();
                Log.i(TAG, routeLists.toString());
                Observable.fromIterable(routeLists.getRoutes())
                        .subscribeOn(getSchedulerProvider().io())
                        .filter(v -> Long.valueOf(v.getId()) == id)
                        .toList()
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new SingleObserver<List<Routes>>() {

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Routes> routes) {
                                getView().onRoutesDetailsLoaded(routes);
                                getView().hideLoading();
                            }

                            @Override
                            public void onError(Throwable e) {
                                getView().onUnknownError(e.getMessage());
                            }
                        });
            } else {
                getView().onConnectionError();
            }
        }
    }
}
