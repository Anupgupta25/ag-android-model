package com.mm.product.client.route;

import android.app.Application;

import com.mm.product.client.route.utills.AppLogger;
import com.mm.product.client.route.data.network.api.ApiEndpoint;
import com.mm.product.client.route.di.component.ApplicationComponent;
import com.mm.product.client.route.di.component.DaggerApplicationComponent;
import com.mm.product.client.route.di.module.ApplicationModule;
import com.mm.product.client.route.di.module.NetworkModule;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public class App extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initApplicationComponent();

        AppLogger.init();
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(ApiEndpoint.BASE_URL))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
