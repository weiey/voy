package com.voy.ui.activity;

import android.os.Bundle;
import android.view.View;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.voy.R;
import com.voy.eventbus.EventCenter;
import com.voy.netstatus.NetUtils;
import com.voy.ui.activity.base.BaseActivity;
import com.voy.widgets.SmoothImageView;

import butterknife.InjectView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class ImagesDetailActivity extends BaseActivity {

    public static final String INTENT_IMAGE_URL_TAG = "INTENT_IMAGE_URL_TAG";
    public static final String INTENT_IMAGE_X_TAG = "INTENT_IMAGE_X_TAG";
    public static final String INTENT_IMAGE_Y_TAG = "INTENT_IMAGE_Y_TAG";
    public static final String INTENT_IMAGE_W_TAG = "INTENT_IMAGE_W_TAG";
    public static final String INTENT_IMAGE_H_TAG = "INTENT_IMAGE_H_TAG";

    private String mImageUrl;
    private int mLocationX;
    private int mLocationY;
    private int mWidth;
    private int mHeight;


    SmoothImageView mSmoothImageView;


//    @Override
//    protected void getBundleExtras(Bundle extras) {
//        mImageUrl = extras.getString(INTENT_IMAGE_URL_TAG);
//        mLocationX = extras.getInt(INTENT_IMAGE_X_TAG);
//        mLocationY = extras.getInt(INTENT_IMAGE_Y_TAG);
//        mWidth = extras.getInt(INTENT_IMAGE_W_TAG);
//        mHeight = extras.getInt(INTENT_IMAGE_H_TAG);
//    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    public void onBackPressed() {
        mSmoothImageView.transformOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }



    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_images_detail;
    }

//    @Override
//    protected void onEventComming(EventCenter eventCenter) {
//
//    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initView() {
        mSmoothImageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        mSmoothImageView.transformIn();

        ImageLoader.getInstance().displayImage(mImageUrl, mSmoothImageView);

        mSmoothImageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 2) {
                    finish();
                }
            }
        });

        mSmoothImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v2) {
                mSmoothImageView.transformOut();
            }
        });
    }

//    @Override
//    protected void onNetworkConnected(NetUtils.NetType type) {
//
//    }
//
//    @Override
//    protected void onNetworkDisConnected() {
//
//    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }
}
