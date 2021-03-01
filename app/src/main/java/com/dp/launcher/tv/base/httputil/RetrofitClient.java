package com.dp.launcher.tv.base.httputil;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by LiCola on  2016/04/16  0:08
 */
public class RetrofitClient {

    //所有的联网地址 统一成https
    public static String mBaseUrl = "http://devdcpapi.cloudp.cc/";
    public static Gson gson = new Gson();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static DevicesAPI devicesAPI;


    public static DevicesAPI getDevicesAPI(){
        if(devicesAPI==null){
            Retrofit retrofit = builder
                    .client(OkHttpHelper.addLogClient(httpClient).build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            devicesAPI = retrofit.create(DevicesAPI.class);
        }
        
        return devicesAPI;
    }



    public static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(mBaseUrl)
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

}
