package com.voy.interactor;

import android.content.Context;

import com.voy.bean.BaseEntity;

import java.util.List;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public interface CommonContainerInteractor {
    List<BaseEntity> getCommonCategoryList(Context context);
}
