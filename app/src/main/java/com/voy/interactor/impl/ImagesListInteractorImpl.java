package com.voy.interactor.impl;

import com.voy.AppConstants;
import com.voy.bean.ResponseImagesListEntity;
import com.voy.interactor.CommonListInteractor;
import com.voy.listeners.BaseMultiLoadedListener;
import com.voy.net.ApiService;
import com.voy.net.ServiceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public class ImagesListInteractorImpl implements CommonListInteractor {
    private BaseMultiLoadedListener<ResponseImagesListEntity> loadedListener = null;

    public ImagesListInteractorImpl(BaseMultiLoadedListener<ResponseImagesListEntity> loadedListener) {
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonListData(final String requestTag, final int event_tag, String keywords, int page) {
        Call<ResponseImagesListEntity> call = ServiceManager.getInstance().getService().getImagesList(AppConstants.Urls.BAIDU_IMAGES_URLS,keywords,"全部",page * 10,10,1);
        call.enqueue(new Callback<ResponseImagesListEntity>() {
            @Override
            public void onResponse(Call<ResponseImagesListEntity> call, Response<ResponseImagesListEntity> response) {
                loadedListener.onSuccess(event_tag, response.body());
            }

            @Override
            public void onFailure(Call<ResponseImagesListEntity> call, Throwable t) {
                loadedListener.onException(t.getMessage());
            }
        });

    }
}
