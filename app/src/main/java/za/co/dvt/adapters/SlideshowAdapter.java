package za.co.dvt.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import za.co.dvt.constants.BackgroundImageSource;
import za.co.dvt.helpers.BitmapHelper;
import za.co.weatherappdvt.R;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class SlideshowAdapter extends PagerAdapter {

        BitmapHelper bitmapHelper;
        Context mContext;
        LayoutInflater mLayoutInflater;

        public SlideshowAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return BackgroundImageSource.mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.image_view_layout, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.background_image);
            imageView.setImageBitmap(bitmapHelper.decodeBitmapFromResource(mContext.getResources(), BackgroundImageSource.mResources[position], 640, 480));
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
}
