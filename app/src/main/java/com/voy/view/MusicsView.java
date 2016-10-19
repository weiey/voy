package com.voy.view;

import com.voy.bean.MusicsListEntity;
import com.voy.bean.ResponseMusicsListentity;
import com.voy.view.base.BaseView;


public interface MusicsView extends BaseView {

    void refreshMusicsList(ResponseMusicsListentity data);

    void addMoreMusicsList(ResponseMusicsListentity data);

    void rePlayMusic();

    void startPlayMusic();

    void stopPlayMusic();

    void pausePlayMusic();

    void playNextMusic();

    void playPrevMusic();

    void seekToPosition(int position);

    void refreshPageInfo(MusicsListEntity entity, int totalDuration);

    void refreshPlayProgress(int progress);

    void refreshPlaySecondProgress(int progress);
}
