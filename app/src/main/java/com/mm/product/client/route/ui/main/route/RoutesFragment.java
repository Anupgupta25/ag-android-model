package com.mm.product.client.route.ui.main.route;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.mm.product.client.route.R;
import com.mm.product.client.route.data.model.RouteLists;
import com.mm.product.client.route.data.model.Routes;
import com.mm.product.client.route.ui.base.BaseFragment;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public class RoutesFragment extends BaseFragment{

    private static final String KEY_ROUTES = "key.routeslist";

    @BindView(R.id.routes_recyclerview)
    RecyclerView routesRecyclerView;

    public static RoutesFragment newInstance(RouteLists routes) {
        Bundle b = new Bundle();
        b.putParcelable(KEY_ROUTES, Parcels.wrap(routes.getRoutes()));
        RoutesFragment fragment = new RoutesFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.fragment_routes;
    }

    @Override
    protected void setupToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(null);
    }

    @Override
    protected void populateUI(LayoutInflater inflater, View rootView, Bundle savedInstanceState) {
        List<Routes> routesList = Parcels.unwrap(getArguments().getParcelable(KEY_ROUTES));
        RoutesAdapter adapter = new RoutesAdapter(getActivity(), routesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        routesRecyclerView.setLayoutManager(layoutManager);
        routesRecyclerView.setAdapter(adapter);
    }
}
