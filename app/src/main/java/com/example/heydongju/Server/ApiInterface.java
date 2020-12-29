package com.example.heydongju.Server;

import com.example.heydongju.Data.JoinData;
import com.example.heydongju.Data.ReqData;
import com.example.heydongju.Data.ResData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    // base_url + "api/login" 으로 POST 통신
    @POST("services/public/login/")
    Call<ResData> requestPostLogin(@Body ReqData reqData );

    @POST("users/signup/")
    Call<JoinData> requestJoin(@Body JoinData joinData );   // @Body : request 파라미터







}