package com.voy.interactor.impl;


import com.voy.bean.MusicsListEntity;
import com.voy.bean.ResponseImagesListEntity;
import com.voy.bean.ResponseMusicsListentity;
import com.voy.interactor.MusicsInteractor;
import com.voy.listeners.BaseMultiLoadedListener;
import com.voy.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class MusicsInteracotrImpl implements MusicsInteractor {

    private BaseMultiLoadedListener<ResponseMusicsListentity> loadedListener = null;

    public MusicsInteracotrImpl(BaseMultiLoadedListener<ResponseMusicsListentity> loadedListener) {
        this.loadedListener = loadedListener;
    }

    @Override
    public void getMusicListData(String requestTag, String keywords, final int event_tag) {


        String json = "{\"song\":[{\"status\":0,\"picture\":\"https://img3.doubanio.com\\/f\\/fm\\/ee35f1c5b60b81047cf82cd7289b2f668a03f9fc\\/pics\\/fm\\/landingpage\\/qr@2x.png\",\"artist\":\"豆瓣FM https:\\/\\/douban.fm\",\"ver\":0,\"alert_msg\":\"\",\"sha256\":\"61832d23a8ed139b21e03ff1c73396ebe990e4e30f0c70dc88c8cfd1aca385a1\",\"albumtitle\":\"豆瓣FM\",\"promo\":{},\"title\":\"API已升级, 请下载豆瓣FM App继续收听。感谢理解。\",\"ssid\":\"5bf5\",\"length\":309,\"url\":\"http:\\/\\/mr7.doubanio.com\\/9b0f67501f975dfbcec7c815c81b0c22\\/0\\/fm\\/song\\/p1831618_128k.mp4\",\"subtype\":\"S\",\"singers\":[],\"aid\":\"131100\",\"file_ext\":\"mp4\",\"album\":\"https:\\/\\/douban.fm\\/\",\"kbps\":\"128\",\"sid\":\"1831618\",\"like\":0}],\"is_show_quick_start\":0,\"r\":0,\"version_max\":102,\"logout\":1,\"warning\":\"user_is_ananymous\"}";
        ResponseMusicsListentity musicsListentity = (ResponseMusicsListentity) JsonUtils.jsonToBean(json, ResponseMusicsListentity.class);
        loadedListener.onSuccess(event_tag, musicsListentity);
//        TLog.d(requestTag, UriHelper.getInstance().getDoubanPlayListUrl(keywords));
//        GsonRequest<ResponseMusicsListentity> gsonRequest = new GsonRequest<ResponseMusicsListentity>(
//                UriHelper.getInstance().getDoubanPlayListUrl(keywords),
//                null,
//                new TypeToken<ResponseMusicsListentity>() {
//                }.getType(),
//                new Response.Listener<ResponseMusicsListentity>() {
//                    @Override
//                    public void onResponse(ResponseMusicsListentity response) {
//                        loadedListener.onSuccess(event_tag, response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        loadedListener.onException(error.getMessage());
//                    }
//                }
//        );
//
//        gsonRequest.setShouldCache(true);
//        gsonRequest.setTag(requestTag);
//
//        VolleyHelper.getInstance().getRequestQueue().add(gsonRequest);
    }
}
