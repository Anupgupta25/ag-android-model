package com.mm.product.client.route.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.mm.product.client.route.R;
import com.mm.product.client.route.utills.FontCache;

/**
 * Created by anup.gupta on 11/15/2017. hrskrs on 7/15/2016.
 */
public class HKButton extends AppCompatButton {

  private static final String BACKSLASH = "/";
  private String fontSrcDirectory = "fonts" + BACKSLASH;
  private static final String fontExtension = ".ttf";
  private String fontName = "Roboto-Medium";

  public HKButton(Context context) {
    super(context);
    setCustomFont(context, null);
  }

  public HKButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    setCustomFont(context, attrs);
  }

  public HKButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setCustomFont(context, attrs);
  }

  @SuppressLint("CustomViewStyleable")
  private void setCustomFont(Context context, AttributeSet attrs) {
    if (attrs != null) {
      TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HKViewAttrs);
      if (ta != null) {
        String userCustomFont = ta.getString(R.styleable.HKViewAttrs_hkCustomFont);
        if (!TextUtils.isEmpty(userCustomFont)) {
          fontName = userCustomFont;
        }

        ta.recycle();
      }
    }

    Typeface typeface = FontCache.getTypeface(fontSrcDirectory + fontName +
        fontExtension, context);
    setTypeface(typeface);
  }

  public void setFontSrcDirectory(String fontSrcDirectory) {
    this.fontSrcDirectory = fontSrcDirectory;
  }

  public String getFontSrcDirectory() {
    return fontSrcDirectory;
  }

  public void setFontName(String fontName) {
    this.fontName = fontName;
  }

  public String getFontName() {
    return fontName;
  }
}
