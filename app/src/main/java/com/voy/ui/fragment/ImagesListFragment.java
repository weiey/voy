package com.voy.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.voy.AppConstants;
import com.voy.R;
import com.voy.adapter.CommonAdapter;
import com.voy.adapter.ViewHolder;
import com.voy.bean.ImagesListEntity;
import com.voy.bean.ResponseImagesListEntity;
import com.voy.common.OnCommonPageSelectedListener;
import com.voy.netstatus.NetUtils;
import com.voy.presenter.ImagesListPresenter;
import com.voy.presenter.impl.ImagesListPresenterImpl;
import com.voy.ui.fragment.base.BaseLazyFragment;
import com.voy.view.ImagesListView;
import com.voy.widgets.PLAImageView;
import com.voy.widgets.PLALoadMoreListView;
import com.voy.widgets.XSwipeRefreshLayout;
import com.voy.widgets.pla.PLAAdapterView;

public class ImagesListFragment extends BaseLazyFragment implements ImagesListView,OnCommonPageSelectedListener,
        PLALoadMoreListView.OnLoadMoreListener,PLAAdapterView.OnItemClickListener,XSwipeRefreshLayout.OnRefreshListener{
    /**
     * the page number
     */
    private int mCurrentPage = 0;


    XSwipeRefreshLayout mSwipeRefreshLayout;



    PLALoadMoreListView mListView;
    private String mCurrentImagesCategory;

    private ImagesListPresenter mImagesListPresenter = null;
    private CommonAdapter mListViewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentImagesCategory = getResources().getStringArray(R.array.images_category_list_id)[0];
    }
    @Override
    protected int getContentViewLayoutID() {
         return R.layout.fragment_images_list;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = $(R.id.fragment_images_list_swipe_layout);
        mListView =  $(R.id.fragment_images_list_list_view);

        mListViewAdapter = new CommonAdapter<ImagesListEntity>(getActivity(),R.layout.list_item_images_list) {
            @Override
            public void convert(ViewHolder holder, ImagesListEntity itemData) {
                PLAImageView mItemImage = holder.getView(R.id.list_item_images_list_image);

                int width = itemData.getThumbnailWidth();
                int height = itemData.getThumbnailHeight();

                String imageUrl = itemData.getThumbnailUrl();

                if (!TextUtils.isEmpty(imageUrl)) {
                    ImageLoader.getInstance().displayImage(imageUrl, mItemImage);
                }

                mItemImage.setImageWidth(width);
                mItemImage.setImageHeight(height);
            }
        };


        mListView.setOnItemClickListener(this);
        mListView.setOnLoadMoreListener(this);
        mListView.setAdapter(mListViewAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onFirstUserVisible() {
        mCurrentPage = 0;
        mImagesListPresenter = new ImagesListPresenterImpl(mContext, this);
        if (NetUtils.isNetworkConnected(mContext)) {
            if (null != mSwipeRefreshLayout) {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mImagesListPresenter.loadListData(TAG_LOG, AppConstants.Events.EVENT_REFRESH_DATA, mCurrentImagesCategory,
                                mCurrentPage, false);
                    }
                }, 200);
            }
        } else {
            toggleNetworkError(true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImagesListPresenter.loadListData(TAG_LOG, AppConstants.Events.EVENT_REFRESH_DATA, mCurrentImagesCategory,  mCurrentPage, false);
                }
            });
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return mSwipeRefreshLayout;
    }

    @Override
    public void onItemClick(PLAAdapterView<?> parent, View view, int position, long id) {

    }



    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        mImagesListPresenter.loadListData(TAG_LOG, AppConstants.Events.EVENT_REFRESH_DATA, mCurrentImagesCategory, mCurrentPage,  true);
    }

    @Override
    public void onLoadMore() {
        mCurrentPage++;
        mImagesListPresenter.loadListData(TAG_LOG, AppConstants.Events.EVENT_LOAD_MORE_DATA, mCurrentImagesCategory, mCurrentPage, true);
    }

    @Override
    public void refreshListData(ResponseImagesListEntity responseImagesListEntity) {
        if (null != mSwipeRefreshLayout) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if (null != responseImagesListEntity) {
            if (null != mListViewAdapter) {
                mListViewAdapter.setData(responseImagesListEntity.getImgs());
                mListViewAdapter.notifyDataSetChanged();
            }

            if (null != mListView) {
                if (calculateTotalPages(responseImagesListEntity.getTotalNum()) > mCurrentPage) {
                    mListView.setCanLoadMore(true);
                } else {
                    mListView.setCanLoadMore(false);
                }
            }
        }
    }

    @Override
    public void addMoreListData(ResponseImagesListEntity responseImagesListEntity) {
        if (null != mListView) {
            mListView.onLoadMoreComplete();
        }

        if (null != responseImagesListEntity) {
            if (null != mListViewAdapter) {
                mListViewAdapter.addData(responseImagesListEntity.getImgs());
                mListViewAdapter.notifyDataSetChanged();
            }

            if (null != mListView) {
                if (calculateTotalPages(responseImagesListEntity.getTotalNum()) > mCurrentPage) {
                    mListView.setCanLoadMore(true);
                } else {
                    mListView.setCanLoadMore(false);
                }
            }
        }
    }

    @Override
    public void navigateToImagesDetail(int position, ImagesListEntity entity, int x, int y, int width, int height) {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showException(String msg) {

    }

    @Override
    public void showNetError() {

    }

    public int calculateTotalPages(int totalNumber) {
        if (totalNumber > 0) {
            return totalNumber % 20 != 0 ? (totalNumber / 20 + 1) : totalNumber / 20;
        } else {
            return 0;
        }
    }

    @Override
    public void onPageSelected(int position, String keywords) {
        mCurrentImagesCategory = keywords;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }
}
