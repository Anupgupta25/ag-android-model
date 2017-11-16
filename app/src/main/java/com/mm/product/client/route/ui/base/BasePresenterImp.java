package com.mm.product.client.route.ui.base;

import com.mm.product.client.route.data.network.api.ApiManager;
import com.mm.product.client.route.utills.rx.schedulers.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public class BasePresenterImp<V extends BaseView> implements BasePresenter<V> {

    private static final String TAG = "BasePresenter";
    private final ApiManager apiManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    @Inject
    public BasePresenterImp(ApiManager apiManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        this.apiManager = apiManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        mMvpView = null;
    }

    public boolean isViewAttached() { return mMvpView != null; }

    public V getView() {
        return mMvpView;
    }

    public ApiManager getApiManager() {
        return apiManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

}
