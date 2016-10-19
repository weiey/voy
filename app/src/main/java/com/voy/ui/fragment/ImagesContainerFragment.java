package com.voy.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.voy.R;
import com.voy.adapter.ImagesContainerPagerAdapter;
import com.voy.bean.BaseEntity;
import com.voy.presenter.Presenter;
import com.voy.presenter.impl.ImagesContainerPresenterImpl;
import com.voy.ui.fragment.base.BaseLazyFragment;
import com.voy.view.CommonContainerView;
import com.voy.widgets.XViewPager;
import com.voy.widgets.smartlayout.SmartTabLayout;

import java.util.List;
/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public class ImagesContainerFragment extends BaseLazyFragment  implements CommonContainerView {

    XViewPager mViewPager;
    SmartTabLayout mSmartTabLayout;

    private Presenter mImagesContainerPresenter = null;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_images;
    }

    @Override
    protected void initView() {
        mViewPager = $(R.id.fragment_images_pager);
        mSmartTabLayout = $(R.id.fragment_images_tab_smart);

    }
    @Override
    protected void onFirstUserVisible() {
        mImagesContainerPresenter = new ImagesContainerPresenterImpl(mContext, this);
        mImagesContainerPresenter.initialized();
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }






    @Override
    public void initializePagerViews(final List<BaseEntity> categoryList) {
        if (null != categoryList && !categoryList.isEmpty()) {
            mViewPager.setOffscreenPageLimit(categoryList.size());
            mViewPager.setAdapter(new ImagesContainerPagerAdapter(getSupportFragmentManager(), categoryList));
            mSmartTabLayout.setViewPager(mViewPager);
            mSmartTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    ImagesListFragment fragment = (ImagesListFragment) mViewPager.getAdapter().instantiateItem(mViewPager, position);
                    fragment.onPageSelected(position, categoryList.get(position).getId());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }
}
