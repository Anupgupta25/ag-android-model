package com.mm.product.client.route.utills;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public class FontCache {

    private static HashMap<String, Typeface> typeFaces = new HashMap<>();

    private FontCache() {
        throw new AssertionError();
    }

    public static Typeface getTypeface(String fontName, Context context) {
        Typeface typeface = typeFaces.get(fontName);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            } catch (Exception e) {
                return null;
            }
            typeFaces.put(fontName, typeface);
        }

        return typeface;
    }

}
