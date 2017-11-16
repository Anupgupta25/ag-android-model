package com.mm.product.client.route.ui.main;

import android.os.Bundle;

import com.mm.product.client.route.data.model.RouteLists;
import com.mm.product.client.route.data.model.Routes;
import com.mm.product.client.route.ui.base.BaseActivity;
import com.mm.product.client.route.ui.event.RoutesListClickEvent;
import com.mm.product.client.route.ui.main.route.RoutesFragment;
import com.mm.product.client.route.ui.main.route_details.RouteDetailsFragment;
import com.mm.product.client.route.utills.rx.RxEvent;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView{

    RouteLists routes;

    @Inject
    MainPresenter<MainView> mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        if (savedInstanceState == null) {
            mainPresenter.onAttach(this);
            mainPresenter.getRoutes();
        }

        RxEvent.getInstance().getEvents().subscribe(o -> {
            if (o instanceof RoutesListClickEvent) {
                RoutesListClickEvent event = (RoutesListClickEvent) o;
                mainPresenter.getRoutesDetails(event.getId(), routes);
            }
        });
    }

    @Override
    public void onRoutesLoaded(RouteLists routes) {
        addFragment(RoutesFragment.newInstance(routes));
        this.routes = routes;
    }

    @Override
    public void onRoutesDetailsLoaded(List<Routes> route) {
        replaceFragment(RouteDetailsFragment.newInstance(route), true);
    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDetach();
        super.onDestroy();
    }
}
