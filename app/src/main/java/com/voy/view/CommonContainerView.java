package com.voy.view;

import com.voy.bean.BaseEntity;

import java.util.List;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public interface CommonContainerView {
    void initializePagerViews(List<BaseEntity> categoryList);
}
