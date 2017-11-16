package com.mm.product.client.route.utills;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.mm.product.client.route.R;
import com.mm.product.client.route.ui.view.HKImageView;


/**
 * Created by anup.gupta on 11/15/2017.
 */

public final class ImageUtils {

  private ImageUtils() {
    throw new AssertionError();
  }

  public static void loadImage(Context context, String imageUrl, HKImageView view) {
    Glide.with(context).load(imageUrl).asBitmap().placeholder(R.drawable.ic_unhappy).into(view);
  }

  public static void loadImage(Context context, Integer imageUrl, HKImageView view) {
    Glide.with(context).load(imageUrl).asBitmap().placeholder(R.drawable.ic_unhappy).into(view);
  }
}
