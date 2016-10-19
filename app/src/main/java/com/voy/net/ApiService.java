package com.voy.net;

import com.voy.bean.ResponseImagesListEntity;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface ApiService {
    /**
     * 链接后拼接参数
     * @param ip
     * @return
     */
//    @GET("service/getIpInfo.php")
//    Call<Result<IpInfo>> getIpInfo(@Query("ip") String ip);

    /**
     * 补充路径
     * @param id
     * @return
     */
    @GET("book/{id}")
    Call<Result> getBook(@Path("id") String id);
    /***
     * 同名 多值
     * @param name
     * @return
     */
    @GET("book/search")
    Call<Result> getSearchBooks(@Query("q") List<String> name);

    /**
     * 多名多值
     * @param options 可为空
     * @return
     */
    @GET("book/search")
    Call<Result> getSearchBooks(@QueryMap Map<String, String> options);


    @FormUrlEncoded
    @POST("book/reviews")
    Call<String> addReviews(@Field("book") String bookId, @Field("title") String title, @Field("content") String content, @Field("rating") String rating);

    @FormUrlEncoded
    @POST("book/reviews")
    Call<Result> addReviews(@FieldMap Map<String, String> fields);

//    @FormUrlEncoded
//    @POST("book/reviews")
//    Call<Result> addReviews(@Body IpInfo ipInfo);


    /**
     * 还需要在Activity和Fragment中实现两个工具方法，代码如下：

     public static final String MULTIPART_FORM_DATA = "multipart/form-data";

     @NonNull
     private RequestBody createPartFromString(String descriptionString) {
     return RequestBody.create(
     MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
     }

     @NonNull
     private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
     File file = FileUtils.getFile(this, fileUri);

     // 为file建立RequestBody实例
     RequestBody requestFile =
     RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

     // MultipartBody.Part借助文件名完成最终的上传
     return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
     }

     */
    // 上传单个文件
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadFile(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);
    // 上传多个文件
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadMultipleFiles(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2);


    /***
     * 添加自定义的header
     * @param name
     * @param tag
     * @param start
     * @param count
     * @return
     */
    @Headers("Cache-Control: max-age=640000")
    @GET("book/search")
    Call<RequestBody> getSearchBooks(@Query("q") String name,
                                     @Query("tag") String tag, @Query("start") int start,
                                     @Query("count") int count);

    /***
     * 多个header参数
     * @param name
     * @param tag
     * @param start
     * @return
     */
    @Headers({
            "Accept: application/vnd.yourapi.v1.full+json",
            "User-Agent: Your-App-Name"
    })
    @GET("book/search")
    Call<RequestBody> getSearchBooks(@Query("q") String name, @Query("tag") String tag, @Query("start") int start);


    // 上传单个文件
    @Multipart
    @POST("log_add.action")
    Call<ResponseBody> logAdd(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);

    // 上传多个文件
    @Multipart
    @POST("log_add.action")
    Call<ResponseBody> logAddMultipleFiles(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2);

    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    @GET
    Call<ResponseImagesListEntity> getImagesList(@Url String fileUrl,@Query("col") String col,@Query("tag") String tag,@Query("pn")int pn,@Query("rn") int rn,@Query("from") int from);
}
