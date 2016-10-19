
package com.voy;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;
import com.voy.net.ServiceManager;
import com.voy.utils.ImageLoaderHelper;

public class AppContext extends Application {

    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;


    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.openActivityDurationTrack(false);
        ServiceManager.getInstance().init(this);
        ImageLoader.getInstance().init(ImageLoaderHelper.getInstance(this).getImageLoaderConfiguration(AppConstants.Paths.IMAGE_LOADER_CACHE_PATH));
    }


    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }
}
