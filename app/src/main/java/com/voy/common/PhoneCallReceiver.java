package com.voy.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
public class PhoneCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == intent) {
            return;
        }

        String action = intent.getAction();

        if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
           // EventBus.getDefault().post(new EventCenter(Constants.EVENT_STOP_PLAY_MUSIC));
        } else {
            final TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            telephonyManager.listen(new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                    switch (state) {
                        case TelephonyManager.CALL_STATE_OFFHOOK:
                        case TelephonyManager.CALL_STATE_RINGING:
                           // EventBus.getDefault().post(new EventCenter(Constants.EVENT_STOP_PLAY_MUSIC));

                            break;

                        case TelephonyManager.CALL_STATE_IDLE:
                            //EventBus.getDefault().post(new EventCenter(Constants.EVENT_START_PLAY_MUSIC));

                            break;
                    }
                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }
}
