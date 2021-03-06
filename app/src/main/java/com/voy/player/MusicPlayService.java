package com.voy.player;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.voy.AppConstants;
import com.voy.bean.MusicsListEntity;
import java.util.List;

public class MusicPlayService extends Service {

    private static MusicPlayer mPlayer = null;
    private PlayBroadCastReceiver mBroadCastReceiver = null;
    private PhoneCallReceiver mPhoneCallReceiver = null;
    private TelephonyManager mTelephonyManager = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MusicPlayer(this);

        mBroadCastReceiver = new PlayBroadCastReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(MusicPlayState.ACTION_MUSIC_NEXT);
        filter.addAction(MusicPlayState.ACTION_MUSIC_PAUSE);
        filter.addAction(MusicPlayState.ACTION_MUSIC_PLAY);
        filter.addAction(MusicPlayState.ACTION_MUSIC_PREV);
        filter.addAction(MusicPlayState.ACTION_MUSIC_REPLAY);
        filter.addAction(MusicPlayState.ACTION_MUSIC_STOP);
        filter.addAction(MusicPlayState.ACTION_EXIT);
        filter.addAction(MusicPlayState.ACTION_SEEK_TO);

        registerReceiver(mBroadCastReceiver, filter);

        mPhoneCallReceiver = new PhoneCallReceiver();

        IntentFilter phoneCallFilter = new IntentFilter();
        phoneCallFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);

        registerReceiver(mPhoneCallReceiver, phoneCallFilter);

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyManager.listen(new PhoneStateChangedListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadCastReceiver);
        unregisterReceiver(mPhoneCallReceiver);

        mTelephonyManager.listen(null, PhoneStateListener.LISTEN_NONE);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private class PlayBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MusicPlayState.ACTION_MUSIC_NEXT)) {
                mPlayer.playNext();
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_PAUSE)) {
                mPlayer.pause();
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_PLAY)) {
                mPlayer.play();
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_PREV)) {
                mPlayer.playPrev();
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_REPLAY)) {
                mPlayer.replay();
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_STOP)) {
                mPlayer.stop();
            } else if (action.equals(MusicPlayState.ACTION_EXIT)) {
                mPlayer.exit();
            } else if (action.equals(MusicPlayState.ACTION_SEEK_TO)) {
                int progress = intent.getIntExtra(AppConstants.KEY_PLAYER_SEEK_TO_PROGRESS, 0);
                mPlayer.seekTo(progress);
            }
        }

    }

    public static void refreshMusicList(List<MusicsListEntity> listData) {
        if (null != listData && !listData.isEmpty()) {
            mPlayer.refreshMusicList(listData.get(0));
        }
    }

    private class PhoneCallReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (null == intent) {
                return;
            }

            String action = intent.getAction();

            if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
                //EventBus.getDefault().post(new EventCenter(Constants.EVENT_STOP_PLAY_MUSIC));
            }
        }
    }

    private class PhoneStateChangedListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_OFFHOOK:
                case TelephonyManager.CALL_STATE_RINGING:
                   // EventBus.getDefault().post(new EventCenter(Constants.EVENT_STOP_PLAY_MUSIC));

                    break;

                case TelephonyManager.CALL_STATE_IDLE:
                   // EventBus.getDefault().post(new EventCenter(Constants.EVENT_START_PLAY_MUSIC));

                    break;
            }
        }
    }
}
