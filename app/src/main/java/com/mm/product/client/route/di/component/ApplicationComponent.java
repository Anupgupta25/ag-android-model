package com.mm.product.client.route.di.component;

import android.app.Application;
import android.content.Context;

import com.mm.product.client.route.App;
import com.mm.product.client.route.data.network.api.ApiManager;
import com.mm.product.client.route.di.ApplicationContext;
import com.mm.product.client.route.di.module.ApplicationModule;
import com.mm.product.client.route.di.module.NetworkModule;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by anup.gupta on 11/15/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(App myApp);

    @ApplicationContext
    Context getContext();

    Application application();

    ApiManager apiManager();

}
