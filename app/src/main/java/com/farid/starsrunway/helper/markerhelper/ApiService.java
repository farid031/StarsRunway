package com.farid.starsrunway.helper.markerhelper;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by putuguna on 30/09/17.
 */

public interface ApiService {
    @GET("read_in.php")
    Call<ListLocationModel> getAllLocation();
}