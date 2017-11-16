package com.mm.product.client.route.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public abstract void populateItem(T t);

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    
}
