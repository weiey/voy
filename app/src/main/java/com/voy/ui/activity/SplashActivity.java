package com.voy.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.voy.R;
import com.voy.ui.activity.base.BaseActivity;

import java.util.Calendar;


public class SplashActivity extends BaseActivity {

    ImageView splash_image;
    TextView splash_version_name;
    TextView splash_copyright;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }
    @Override
    protected void initView() {
        splash_copyright = $(R.id.splash_copyright);
        splash_version_name = $(R.id.splash_version_name);
        splash_image = $(R.id.splash_image);

        splash_copyright.setText(R.string.splash_copyright);
        splash_version_name.setText(getVersionName());
        splash_image.setImageResource(getBackgroundImageResID());

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                 toNext();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splash_image.startAnimation(animation);
    }

    private void toNext() {
       toThenKill(MainActivity.class);
    }


    private int getBackgroundImageResID() {
        int resId;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 12) {
            resId = R.drawable.morning;
        } else if (hour > 12 && hour <= 18) {
            resId = R.drawable.afternoon;
        } else {
            resId = R.drawable.night;
        }
        return resId;
    }

    public String getVersionName() {
        String versionName = null;
        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.format(getResources().getString(R.string.splash_version), versionName);
    }

}
