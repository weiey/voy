package com.voy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.voy.bean.BaseEntity;
import com.voy.ui.fragment.ImagesListFragment;

import java.util.List;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public class ImagesContainerPagerAdapter extends FragmentPagerAdapter {
    private List<BaseEntity> mCategoryList = null;

    public ImagesContainerPagerAdapter(FragmentManager fm, List<BaseEntity> categoryList) {
        super(fm);
        mCategoryList = categoryList;
    }

    @Override
    public Fragment getItem(int position) {
        return new ImagesListFragment();
    }

    @Override
    public int getCount() {
        return null != mCategoryList ? mCategoryList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null != mCategoryList ? mCategoryList.get(position).getName() : null;
    }
}
