/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.voy.interactor.impl;

import android.content.Context;

import com.voy.R;
import com.voy.bean.NavigationEntity;
import com.voy.interactor.HomeInteractor;
import com.voy.ui.fragment.ImagesContainerFragment;
import com.voy.ui.fragment.MusicsFragment;
import com.voy.ui.fragment.base.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeInteractorImpl implements HomeInteractor {

    @Override
    public List<BaseLazyFragment> getPagerFragments() {
        List<BaseLazyFragment> fragments = new ArrayList<BaseLazyFragment>();
        fragments.add(new ImagesContainerFragment());
        fragments.add(new ImagesContainerFragment());
        fragments.add(new MusicsFragment());
        return fragments;
    }

    @Override
    public List<NavigationEntity> getNavigationListData(Context context) {
        List<NavigationEntity> navigationEntities = new ArrayList<>();
        String[] navigationArrays = context.getResources().getStringArray(R.array.navigation_list);
        navigationEntities.add(new NavigationEntity("", navigationArrays[0], R.drawable.ic_picture));
        navigationEntities.add(new NavigationEntity("", navigationArrays[1], R.drawable.ic_video));
        navigationEntities.add(new NavigationEntity("", navigationArrays[2], R.drawable.ic_music));
        return navigationEntities;
    }
}
