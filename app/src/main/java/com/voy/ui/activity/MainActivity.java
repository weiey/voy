package com.voy.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.voy.AppManager;
import com.voy.R;
import com.voy.adapter.CommonAdapter;
import com.voy.adapter.VPFragmentAdapter;
import com.voy.adapter.ViewHolder;
import com.voy.bean.NavigationEntity;
import com.voy.presenter.Presenter;
import com.voy.presenter.impl.HomePresenterImpl;
import com.voy.ui.activity.base.BaseActivity;
import com.voy.ui.activity.qrcode.CaptureActivity;
import com.voy.ui.fragment.base.BaseLazyFragment;
import com.voy.utils.ToastUtils;
import com.voy.view.HomeView;
import com.voy.widgets.XViewPager;

import java.util.List;

public class MainActivity extends BaseActivity implements HomeView{


    XViewPager mViewPager;


    ListView mNavListView;


    DrawerLayout mDrawerLayout;

    private int mCheckedListItemColorResIds[] = {
            R.color.navigation_checked_picture_text_color,
            R.color.navigation_checked_video_text_color,
            R.color.navigation_checked_music_text_color,
    };
    private int mCurrentMenuCheckedPos = 0;
    private ActionBarDrawerToggle mActionBarDrawerToggle = null;
    private CommonAdapter<NavigationEntity> mNavListAdapter = null;

    private Presenter mHomePresenter;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initView() {
        mViewPager = $(R.id.home_container);
        mNavListView = $(R.id.home_navigation_list);
        mDrawerLayout = $(R.id.home_drawer);

        mHomePresenter = new HomePresenterImpl(this, this);
        mHomePresenter.initialized();
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
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
    public void initializeViews(List<BaseLazyFragment> fragments, List<NavigationEntity> navigationList) {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (null != mNavListAdapter) {
                    setTitle(mNavListAdapter.getItem(mCurrentMenuCheckedPos).getName());
                }
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        if (null != fragments && !fragments.isEmpty()) {
            mViewPager.setEnableScroll(false);
            mViewPager.setOffscreenPageLimit(fragments.size());
            mViewPager.setAdapter(new VPFragmentAdapter(getSupportFragmentManager(), fragments));
        }

        mNavListAdapter = new CommonAdapter<NavigationEntity>(this,R.layout.list_item_navigation) {
            @Override
            public void convert(ViewHolder holder, NavigationEntity navigationEntity) {
                ImageView itemIcon= holder.getView(R.id.list_item_navigation_icon);
                TextView itemName = holder.getView(R.id.list_item_navigation_name);
                itemIcon.setImageResource(navigationEntity.getIconResId());
                itemName.setText(navigationEntity.getName());

                if (mCurrentMenuCheckedPos == holder.getPosition()) {
                    // checked
                    itemName.setTextColor(getResources().getColor(mCheckedListItemColorResIds[holder.getPosition()]));
                } else {
                    // unchecked
                    itemName.setTextColor(getResources().getColor(android.R.color.black));
                }
            }
        };

        mNavListView.setAdapter(mNavListAdapter);
        mNavListAdapter.setData(navigationList);
        mNavListAdapter.notifyDataSetChanged();
        setTitle(mNavListAdapter.getItem(mCurrentMenuCheckedPos).getName());

        mNavListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentMenuCheckedPos = position;
                mNavListAdapter.notifyDataSetChanged();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mViewPager.setCurrentItem(mCurrentMenuCheckedPos, false);
            }
        });
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.syncState();
        }
    }

    private static long DOUBLE_CLICK_TIME = 0L;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                    ToastUtils.showToast(mContext,"再按次退出应用");
                    DOUBLE_CLICK_TIME = System.currentTimeMillis();
                } else {
                    AppManager.getInstance().exitApp(getApplicationContext());
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle != null && mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_capture:
                to(CaptureActivity.class);
                break;
            case R.id.action_about_us:
//                readyGo(AboutUsActivity.class);
                break;
            case R.id.action_feedback:
//                Bundle extras = new Bundle();
//                extras.putString(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID, mFeedbackAgent.getDefaultConversation().getId());
//                readyGo(FeedBackActivity.class, extras);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
