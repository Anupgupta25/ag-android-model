package com.mm.product.client.route.ui.main.route_details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.mm.product.client.route.R;
import com.mm.product.client.route.data.model.Routes;
import com.mm.product.client.route.ui.base.BaseFragment;
import com.mm.product.client.route.ui.view.HKImageView;
import com.mm.product.client.route.ui.view.HKTextView;
import com.mm.product.client.route.utills.ImageUtils;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;

/**
 * Created by anup.gupta on 11/16/2017.
 */

public class RouteDetailsFragment extends BaseFragment {

    private static final String KEY_ROUTE_DETAILS = "key.route_details";

    @BindView(R.id.item_route_image)
    HKImageView routeImage;
    @BindView(R.id.item_route_tv)
    HKTextView routeTitle;
    @BindView(R.id.item_route_icon)
    HKImageView routeIcon;
    @BindView(R.id.item_route_desc)
    HKTextView routeDesc;
    @BindView(R.id.routes_rv_details)
    RecyclerView routesDetailsRv;

    public static RouteDetailsFragment newInstance(List<Routes> routes) {
        Bundle b = new Bundle();
        b.putParcelable(KEY_ROUTE_DETAILS, Parcels.wrap(routes));
        RouteDetailsFragment fragment = new RouteDetailsFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.fragment_route_details;
    }

    @Override
    protected void setupToolbar(Toolbar toolbar) {
        hideActionBar();
    }

    @Override
    protected void populateUI(LayoutInflater inflater, View rootView, Bundle savedInstanceState) {
        List<Routes> route = Parcels.unwrap(getArguments().getParcelable(KEY_ROUTE_DETAILS));
        ImageUtils.loadImage(getActivity(), route.get(0).getImage(), routeImage);
        ImageUtils.loadImage(getActivity(), R.drawable.icon_accessible, routeIcon);
        routeTitle.setText(route.get(0).getName());
        routeDesc.setText(route.get(0). getDescription());

        RouteDetailsAdapter adapter = new RouteDetailsAdapter(getActivity(), route.get(0).getStops());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        routesDetailsRv.setLayoutManager(layoutManager);
        routesDetailsRv.setAdapter(adapter);
    }
}
