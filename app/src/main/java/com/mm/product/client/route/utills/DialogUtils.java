package com.mm.product.client.route.utills;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.mm.product.client.route.R;
import com.mm.product.client.route.ui.view.HKTextView;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public class DialogUtils {

    private DialogUtils() {
        throw new AssertionError();
    }

    public static ProgressDialog showLoading(Context context) {
        return showLoading(context, "", false);
    }

    public static ProgressDialog showLoading(Context context, boolean isCancelable) {
        return showLoading(context, "", isCancelable);
    }

    public static ProgressDialog showLoading(Context context, String message) {
        return showLoading(context, message, false);
    }

    public static ProgressDialog showLoading(Context context, @StringRes int message) {
        return showLoading(context, context.getString(message), false);
    }

    public static ProgressDialog showLoading(Context context,
                                             @StringRes int message,
                                             boolean isCancelable) {
        return showLoading(context, context.getString(message), isCancelable);
    }

    @SuppressLint("InflateParams")
    private static ProgressDialog showLoading(Context context,
                                              String message,
                                              boolean isCancelable) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        Window window = progressDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.progress_dialog, null);
        HKTextView messageTextView = (HKTextView) view.findViewById(R.id.loading_message);
        if (TextUtils.isEmpty(message)) {
            messageTextView.setVisibility(View.GONE);
        } else {
            messageTextView.setText(message);
            messageTextView.setVisibility(View.VISIBLE);
        }
        progressDialog.setContentView(view);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(isCancelable);
        progressDialog.setCanceledOnTouchOutside(isCancelable);
        return progressDialog;
    }
}
