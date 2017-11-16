package com.mm.product.client.route.di.component;


import com.mm.product.client.route.di.PerActivity;
import com.mm.product.client.route.di.module.ActivityModule;
import com.mm.product.client.route.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by anup.gupta on 11/15/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
