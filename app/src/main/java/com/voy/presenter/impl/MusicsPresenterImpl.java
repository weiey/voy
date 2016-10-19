package com.voy.presenter.impl;

import android.content.Context;

import com.voy.AppConstants;
import com.voy.bean.MusicsListEntity;
import com.voy.bean.ResponseMusicsListentity;
import com.voy.interactor.MusicsInteractor;
import com.voy.interactor.impl.MusicsInteracotrImpl;
import com.voy.listeners.BaseMultiLoadedListener;
import com.voy.presenter.MusicsPresenter;
import com.voy.view.MusicsView;

/**
 * Author:yezw on 2016/10/14
 * Description:
 */
public class MusicsPresenterImpl implements MusicsPresenter ,BaseMultiLoadedListener<ResponseMusicsListentity> {

    private Context mContext = null;
    private MusicsView mMusicsView = null;
    private MusicsInteractor mMusicsInteractor = null;

    public MusicsPresenterImpl(Context context, MusicsView musicsView) {
        mContext = context;
        mMusicsView = musicsView;
        mMusicsInteractor = new MusicsInteracotrImpl(this);
    }
    @Override
    public void loadListData(String requestTag, String keywords, int event_tag) {
        mMusicsInteractor.getMusicListData(requestTag, keywords, event_tag);
    }

    @Override
    public void onNextClick() {
        mMusicsView.playNextMusic();
    }

    @Override
    public void onPrevClick() {
        mMusicsView.playPrevMusic();
    }

    @Override
    public void onStartPlay() {
        mMusicsView.startPlayMusic();
    }

    @Override
    public void onPausePlay() {
        mMusicsView.pausePlayMusic();
    }

    @Override
    public void onRePlay() {
        mMusicsView.rePlayMusic();
    }

    @Override
    public void seekTo(int position) {
        mMusicsView.seekToPosition(position);
    }

    @Override
    public void onStopPlay() {
        mMusicsView.stopPlayMusic();
    }

    @Override
    public void refreshPageInfo(MusicsListEntity entity, int totalDuration) {
        mMusicsView.refreshPageInfo(entity, totalDuration);
    }

    @Override
    public void refreshProgress(int progress) {
        mMusicsView.refreshPlayProgress(progress);
    }

    @Override
    public void refreshSecondProgress(int progress) {
        mMusicsView.refreshPlaySecondProgress(progress);
    }

    @Override
    public void onSuccess(int event_tag, ResponseMusicsListentity data) {
        if (event_tag == AppConstants.Events.EVENT_REFRESH_DATA) {
            mMusicsView.refreshMusicsList(data);
        } else if (event_tag == AppConstants.Events.EVENT_LOAD_MORE_DATA) {
            mMusicsView.addMoreMusicsList(data);
        }

    }

    @Override
    public void onError(String msg) {
        mMusicsView.showError(msg);
    }

    @Override
    public void onException(String msg) {
        mMusicsView.showError(msg);
    }
}
