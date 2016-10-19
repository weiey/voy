package com.voy.view;

import com.voy.bean.NavigationEntity;
import com.voy.ui.fragment.base.BaseLazyFragment;

import java.util.List;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public interface HomeView {
    void initializeViews(List<BaseLazyFragment> fragments, List<NavigationEntity> navigationList);
}
