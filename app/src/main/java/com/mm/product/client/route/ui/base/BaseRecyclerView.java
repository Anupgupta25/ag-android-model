package com.mm.product.client.route.ui.base;

import android.support.v7.widget.RecyclerView;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public abstract class BaseRecyclerView<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    protected abstract T getItem(int position);

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        holder.populateItem(getItem(position));
    }
}