package com.mm.product.client.route.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mm.product.client.route.di.component.ActivityComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    @LayoutRes
    protected abstract int getContentLayoutResId();
    protected abstract void setupToolbar(Toolbar toolbar);
    protected abstract void populateUI(LayoutInflater inflater,
                                       View rootView,
                                       Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentLayoutResId(), container, false);
        mUnBinder = ButterKnife.bind(this, rootView);
        if (mActivity != null) {
            setupToolbar(mActivity.toolbar);
        }
        populateUI(inflater, rootView, savedInstanceState);
        return rootView;
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    protected void addFragment(@NonNull Fragment fragment) {
        if (mActivity != null) {
            mActivity.addFragment(fragment);
        }
    }

    protected void replaceFragment(@NonNull Fragment fragment) {
        if (mActivity != null) {
            mActivity.replaceFragment(fragment);
        }
    }

    protected void hideActionBar() {
        if (mActivity != null) {
            mActivity.hideActionBar();
        }
    }

    protected void showActionBar() {
        if (mActivity != null) {
            mActivity.showActionBar();
        }
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void showLoading(String message) {
        if (mActivity != null) {
            mActivity.showLoading(message);
        }
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void onUnknownError(String error) {
        if (mActivity != null) {
            mActivity.onUnknownError(error);
        }
    }

    @Override
    public void onNetworkError() {
        if (mActivity != null) {
            mActivity.onNetworkError();
        }
    }

    @Override
    public void onTimeout() {
        if (mActivity != null) {
            mActivity.onTimeout();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void onConnectionError() {
        if (mActivity != null) {
            mActivity.onConnectionError();
        }
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }
}
