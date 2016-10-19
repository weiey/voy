package com.voy.presenter;

import com.voy.bean.ImagesListEntity;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public interface ImagesListPresenter {
    void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh);
    void onItemClickListener(int position, ImagesListEntity entity, int x, int y, int width, int height);
}
