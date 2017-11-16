package com.mm.product.client.route.ui.main.route;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mm.product.client.route.R;
import com.mm.product.client.route.data.model.Routes;
import com.mm.product.client.route.ui.base.BaseRecyclerView;
import com.mm.product.client.route.ui.base.BaseViewHolder;
import com.mm.product.client.route.ui.event.RoutesListClickEvent;
import com.mm.product.client.route.ui.view.HKImageView;
import com.mm.product.client.route.ui.view.HKTextView;
import com.mm.product.client.route.utills.ImageUtils;
import com.mm.product.client.route.utills.rx.RxEvent;

import java.util.List;

import butterknife.BindView;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public class RoutesAdapter extends BaseRecyclerView<Routes> {

    private LayoutInflater inflater;
    private List<Routes> routes;

    public RoutesAdapter(Context context, List<Routes> routes){
        this.inflater = LayoutInflater.from(context);
        this.routes = routes;
    }

    @Override
    protected Routes getItem(int position) {
        return routes.get(position);
    }

    @Override
    public BaseViewHolder<Routes> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RouteViewHolder(inflater.inflate(R.layout.item_routes_view, parent, false));
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    static class RouteViewHolder extends BaseViewHolder<Routes> implements View.OnClickListener{

        @BindView(R.id.item_route_image)
        HKImageView routeImage;
        @BindView(R.id.item_route_tv)
        HKTextView routeTv;
        Routes routes;

        public RouteViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void populateItem(Routes routes) {
            this.routes = routes;
            ImageUtils.loadImage(itemView.getContext(), routes.getImage(), routeImage);
            routeTv.setText(routes.getName());
        }

        @Override
        public void onClick(View view) {
            RxEvent.getInstance().postEvent(new RoutesListClickEvent(Long.valueOf(routes.getId())));
        }
    }
}
