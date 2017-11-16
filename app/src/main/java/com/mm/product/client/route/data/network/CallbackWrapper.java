package com.mm.product.client.route.data.network;

import com.mm.product.client.route.ui.base.BaseView;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public abstract class CallbackWrapper <T> extends DisposableObserver<T> {

    private BaseView view;

    public CallbackWrapper(BaseView view) {
        this.view = view;
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        if (t instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) t).response().errorBody();
            view.onUnknownError(getErrorMessage(responseBody));
        } else if (t instanceof SocketTimeoutException) {
            view.onTimeout();
        } else if (t instanceof IOException) {
            view.onNetworkError();
        } else {
            view.onUnknownError(t.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T t);

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
