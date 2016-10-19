package com.voy.presenter.impl;

import android.content.Context;

import com.voy.AppConstants;
import com.voy.R;
import com.voy.bean.ImagesListEntity;
import com.voy.bean.ResponseImagesListEntity;
import com.voy.interactor.CommonListInteractor;
import com.voy.interactor.impl.ImagesListInteractorImpl;
import com.voy.listeners.BaseMultiLoadedListener;
import com.voy.presenter.ImagesListPresenter;
import com.voy.view.ImagesListView;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public class ImagesListPresenterImpl implements ImagesListPresenter, BaseMultiLoadedListener<ResponseImagesListEntity> {

    private Context mContext = null;
    private ImagesListView mImagesListView = null;
    private CommonListInteractor mCommonListInteractor = null;

    public ImagesListPresenterImpl(Context context, ImagesListView imagesListView) {
        mContext = context;
        mImagesListView = imagesListView;
        mCommonListInteractor = new ImagesListInteractorImpl(this);
    }

    @Override
    public void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh) {
        mImagesListView.hideLoading();
        if (!isSwipeRefresh) {
            mImagesListView.showLoading(mContext.getString(R.string.common_loading_message));
        }
        mCommonListInteractor.getCommonListData(requestTag, event_tag, keywords, page);
    }

    @Override
    public void onItemClickListener(int position, ImagesListEntity entity, int x, int y, int width, int height) {

    }

    @Override
    public void onSuccess(int event_tag, ResponseImagesListEntity data) {
        mImagesListView.hideLoading();
        if (event_tag == AppConstants.Events.EVENT_REFRESH_DATA) {
            mImagesListView.refreshListData(data);
        } else if (event_tag == AppConstants.Events.EVENT_LOAD_MORE_DATA) {
            mImagesListView.addMoreListData(data);
        }
    }

    @Override
    public void onError(String msg) {
        mImagesListView.hideLoading();
        mImagesListView.showError(msg);
    }

    @Override
    public void onException(String msg) {
        mImagesListView.hideLoading();
        mImagesListView.showError(msg);
    }
}
