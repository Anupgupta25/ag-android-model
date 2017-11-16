package com.mm.product.client.route.ui.main.route_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mm.product.client.route.R;
import com.mm.product.client.route.data.model.Routes;
import com.mm.product.client.route.data.model.Stop;
import com.mm.product.client.route.ui.base.BaseRecyclerView;
import com.mm.product.client.route.ui.base.BaseViewHolder;
import com.mm.product.client.route.ui.main.route.RoutesAdapter;
import com.mm.product.client.route.ui.view.HKImageView;
import com.mm.product.client.route.ui.view.HKTextView;
import com.mm.product.client.route.utills.ImageUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by anup.gupta on 11/16/2017.
 */

public class RouteDetailsAdapter extends BaseRecyclerView<Stop> {
    private LayoutInflater inflater;
    private static List<Stop> stops;

    public RouteDetailsAdapter(Context context, List<Stop> stops){
        this.inflater = LayoutInflater.from(context);
        this.stops = stops;
    }

    @Override
    protected Stop getItem(int position) {
        return stops.get(position);
    }

    @Override
    public BaseViewHolder<Stop> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RouteDetailsAdapter.RouteDetailsViewHolder(inflater.inflate(R.layout.item_route_details_view, parent, false));
    }

    @Override
    public int getItemCount() {
        return stops.size();
    }

    static class RouteDetailsViewHolder extends BaseViewHolder<Stop> {

        @BindView(R.id.stop_tv)
        HKTextView stopTv;
        @BindView(R.id.line_imageView)
        HKImageView lineImage;

        public RouteDetailsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void populateItem(Stop stop) {
            stopTv.setText(stop.getName());
            if(stops.indexOf(stop) == (stops.size() -1)) {
                lineImage.setVisibility(View.GONE);
            }
        }
    }

}
