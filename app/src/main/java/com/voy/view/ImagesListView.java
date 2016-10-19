package com.voy.view;

import com.voy.bean.ImagesListEntity;
import com.voy.bean.ResponseImagesListEntity;
import com.voy.view.base.BaseView;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public interface ImagesListView extends BaseView{
    void refreshListData(ResponseImagesListEntity responseImagesListEntity);

    void addMoreListData(ResponseImagesListEntity responseImagesListEntity);

    void navigateToImagesDetail(int position, ImagesListEntity entity, int x, int y, int width, int height);
}
