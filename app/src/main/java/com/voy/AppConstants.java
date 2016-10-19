package com.voy;

import android.os.Environment;

/**
 * Author:yezw on 2016/10/13
 * Description:
 */
public class AppConstants {
    public static final class Urls {
        public static final String BAIDU_IMAGES_URLS = "http://image.baidu.com/data/imgs";
        public static final String YOUKU_VIDEOS_URLS = "https://openapi.youku.com/v2/searches/video/by_keyword.json";
        public static final String YOUKU_USER_URLS = "https://openapi.youku.com/v2/users/show.json";
        public static final String DOUBAN_PLAY_LIST_URLS = "http://www.douban.com/j/app/radio/people";
    }
    public static final class Paths {
        public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        public static final String IMAGE_LOADER_CACHE_PATH = "/SimplifyReader/Images/";

    }
    public static final class Events {
        public static final int EVENT_BEGIN = 0X100;
        public static final int EVENT_REFRESH_DATA = EVENT_BEGIN + 10;
        public static final int EVENT_LOAD_MORE_DATA = EVENT_BEGIN + 20;
        public static final int EVENT_START_PLAY_MUSIC = EVENT_BEGIN + 30;
        public static final int EVENT_STOP_PLAY_MUSIC = EVENT_BEGIN + 40;
    }
    public static final String KEY_MUSIC_PARCELABLE_DATA = "KEY_MUSIC_PARCELABLE_DATA";
    public static final String KEY_MUSIC_TOTAL_DURATION = "KEY_MUSIC_TOTAL_DURATION";
    public final static String KEY_MUSIC_CURRENT_DUTATION = "KEY_MUSIC_CURRENT_DUTATION";
    public final static String KEY_MUSIC_SECOND_PROGRESS = "KEY_MUSIC_SECOND_PROGRESS";
    public final static String KEY_PLAYER_SEEK_TO_PROGRESS = "KEY_PLAYER_SEEK_TO_PROGRESS";

    public static final String ACTION_MUSIC_BUNDLE_BROADCAST = "ACTION_MUSIC_BUNDLE_BROADCAST";
    public final static String ACTION_MUSIC_CURRENT_PROGRESS_BROADCAST = "ACTION_MUSIC_CURRENT_PROGRESS_BROADCAST";
    public final static String ACTION_MUSIC_SECOND_PROGRESS_BROADCAST = "ACTION_MUSIC_SECOND_PROGRESS_BROADCAST";
}
