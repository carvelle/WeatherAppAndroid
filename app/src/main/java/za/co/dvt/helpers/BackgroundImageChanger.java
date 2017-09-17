package za.co.dvt.helpers;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import za.co.dvt.constants.BackgroundImageSource;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class BackgroundImageChanger  {

    Timer timer;
    Context context;
    Handler handler;
    ViewPager slideShow;

    public BackgroundImageChanger(Handler handler, Context context, ViewPager slideShow) {
        this.handler = handler;
        this.context = context;
        this.slideShow = slideShow;
    }

    public void pageSwitcher(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new SwitchBackground(), 0, seconds * 1000);
        slideShow.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                view.setTranslationX(view.getWidth() * -position);

                if(position <= -1.0F || position >= 1.0F) {
                    view.setAlpha(0.0F);
                } else if( position == 0.0F ) {
                    view.setAlpha(1.0F);
                } else {
                    view.setAlpha(1.0F - Math.abs(position));
                }
            }
        });
    }

    public class SwitchBackground extends TimerTask  {
        @Override
        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    Random rand = new Random();
                    int  randomImage = rand.nextInt(BackgroundImageSource.mResources.length);
                    slideShow.setCurrentItem(randomImage);
                }
            });
        }
    }
}
