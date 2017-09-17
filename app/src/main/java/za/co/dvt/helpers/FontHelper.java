package za.co.dvt.helpers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import za.co.weatherappdvt.R;


/**
 * Created by sibusiso on 2017/09/16.
 */

public class FontHelper {

    public static void setCustomFont(TextView tv, Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TypefaceTextView, 0, 0);

        try {
            String typefaceName = a.getString(R.styleable.TypefaceTextView_typeface);

            if (!tv.isInEditMode() && !TextUtils.isEmpty(typefaceName)) {
                String path = String.format("fonts/%s.otf", typefaceName);
                Typeface typeface = FontCache.get(path, context);
                if (typeface == null) {
                    typeface = Typeface.createFromAsset(context.getAssets(), path);
                }
                tv.setTypeface(typeface);
                tv.setPaintFlags(tv.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }
        } finally {
            a.recycle();
        }
    }
}
