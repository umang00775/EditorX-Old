package com.umang_rathod.editorx.backend;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiCalls {

    @POST("colorization")
    Call<Colorization> getColoredImage(@Body ColorizationPost colorizationPost);



    @FormUrlEncoded
    @POST("colorization")
    Call<Colorization> getColoredImage(
            @Header("X-API-KEY") String apiKey,
            @Field("url") String imgUrl,
            @Field("return_type") int returnType
    );


    @FormUrlEncoded
    @POST("colorization")
    Call<Colorization> getColoredImage(
            @Header("X-API-KEY") String apiKey,
            @Field("file")File file,
            @Field("url") String url,
            @Field("base64") String base64,
            @Field("sync") int sync,
            @Field("format") String format,
            @Field("return_type") int return_type
    );




//    @FormUrlEncoded
//    @POST("colorization")
//    Call<Colorization> getColoredImage1(
//            @Header("X-Api-Key: pXMPEcU1GWumHYeRBom1dSVv")
//            @Field("url") String URL,
//            @Field("return_type") int returnType
//    );
}
