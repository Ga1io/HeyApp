package com.example.heydongju.Server;

import com.example.heydongju.Data.ReqData;
import com.example.heydongju.Data.ResData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    // base_url + "api/login" 으로 POST 통신
    @POST("services/login/")
    Call<ResData> requestPostLogin(@Body ReqData reqData );   // @Body : request 파라미터





}