package za.co.dvt.Widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import za.co.dvt.helpers.FontHelper;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class TypefaceTextView extends TextView {

    public TypefaceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        FontHelper.setCustomFont(this, context, attrs);
    }

}
