package com.qhiran.uts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search")
    Call<MusicResponse> searchMusic(@Query("q") String query);
}