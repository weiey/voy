package com.voy.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * Author:yezw on 2016/10/12
 * Description:
 */
public class ToastUtils {
    private static int messageShowCount = 0;
    private static int gcCount = 5;

    private static void count() {
        messageShowCount++;
        if (messageShowCount >= gcCount) {
            System.gc();
            messageShowCount = 0;
        }
    }
    /**
     * show toast
     *
     * @param msg
     */
    public static void showToast(View view,String msg) {
        //防止遮盖虚拟按键
        if (null != msg && !TextUtils.isEmpty(msg)) {
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
            count();
        }
    }
    public static void showToast(Context context,String msg) {
         if (null != msg && !TextUtils.isEmpty(msg)) {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }

}
