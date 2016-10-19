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

package com.voy.presenter.impl;

import android.content.Context;

import com.voy.interactor.CommonContainerInteractor;
import com.voy.interactor.impl.ImagesContainerInteractorImpl;
import com.voy.presenter.Presenter;
import com.voy.view.CommonContainerView;

public class ImagesContainerPresenterImpl implements Presenter {

    private Context mContext;
    private CommonContainerInteractor mCommonContainerInteractor;
    private CommonContainerView mCommonContainerView;

    public ImagesContainerPresenterImpl(Context context, CommonContainerView commonContainerView) {
        mContext = context;
        mCommonContainerView = commonContainerView;
        mCommonContainerInteractor = new ImagesContainerInteractorImpl();
    }

    @Override
    public void initialized() {
        mCommonContainerView.initializePagerViews(mCommonContainerInteractor.getCommonCategoryList(mContext));
    }
}
