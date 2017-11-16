package com.mm.product.client.route.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.mm.product.client.route.App;
import com.mm.product.client.route.R;
import com.mm.product.client.route.di.component.ActivityComponent;
import com.mm.product.client.route.di.component.DaggerActivityComponent;
import com.mm.product.client.route.di.module.ActivityModule;
import com.mm.product.client.route.utills.DialogUtils;
import com.mm.product.client.route.utills.FragmentTransactionUtil;
import com.mm.product.client.route.utills.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    private static final String TAG = BaseActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    private ProgressDialog mProgressDialog;
    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;

    @LayoutRes
    protected int getContentViewLayoutResId() {
        return R.layout.activity_main;
    }

    @IdRes
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayoutResId());
        initActivityComponent();
        mUnBinder = ButterKnife.bind(this);
        initToolbar();
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
        }
        //setupToolbar(toolbar);
    }

    protected void hideActionBar() {
        getSupportActionBar().hide();
    }

    protected void showActionBar() {
        getSupportActionBar().show();
    }

    protected void addFragment(@NonNull Fragment fragment) {
        addFragment(fragment, false);
    }

    protected void addFragment(@NonNull Fragment fragment, boolean addToBackStack) {
        FragmentTransactionUtil.addFragment(getSupportFragmentManager(),
                fragment, getFragmentContainerId(), addToBackStack);
    }

    protected void replaceFragment(@NonNull Fragment fragment) {
        replaceFragment(fragment, false);
    }

    protected void replaceFragment(@NonNull Fragment fragment, boolean addToBackStack) {
        FragmentTransactionUtil.replaceFragment(getSupportFragmentManager(),
                fragment, getFragmentContainerId(), addToBackStack);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading(String message) {
        mProgressDialog = DialogUtils.showLoading(this, message);
    }

    @Override
    public void showLoading() {
        mProgressDialog = DialogUtils.showLoading(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
        mProgressDialog = null;
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void onUnknownError(@NonNull String error) {
        Log.e(TAG, error);
        hideLoading();
        //Todo: show error view
    }

    @Override
    public void onNetworkError() {
        Log.e(TAG, "onNetworkError");
        hideLoading();
        //Todo: show network error view
    }

    @Override
    public void onTimeout() {
        Log.e(TAG, "onTimeout");
        hideLoading();
    }

    @Override
    public void onConnectionError() {
        Log.e(TAG, "onConnectionError");
        //TODO: show connection error
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        mActivityComponent = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showActionBar();
    }
}
