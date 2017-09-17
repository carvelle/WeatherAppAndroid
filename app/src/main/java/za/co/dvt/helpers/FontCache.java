package za.co.dvt.helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Hashtable;


/**
 * Created by sibusiso on 2017/09/16.
 */

public class FontCache {

    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                AssetManager am = context.getAssets();
                tf = Typeface.createFromAsset(am, name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}
