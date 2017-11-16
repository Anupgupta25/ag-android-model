package com.mm.product.client.route.di.module;

import android.app.Activity;
import android.content.Context;


import com.mm.product.client.route.di.PerActivity;
import com.mm.product.client.route.ui.main.MainPresenter;
import com.mm.product.client.route.ui.main.MainPresenterImp;
import com.mm.product.client.route.ui.main.MainView;
import com.mm.product.client.route.utills.rx.schedulers.AppSchedulerProvider;
import com.mm.product.client.route.utills.rx.schedulers.SchedulerProvider;
import com.mm.product.client.route.di.ActivityContext;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by anup.gupta on 11/15/2017.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    MainPresenter<MainView> provideMainPresenter(MainPresenterImp<MainView> presenterImp) {
        return presenterImp;
    }
}
