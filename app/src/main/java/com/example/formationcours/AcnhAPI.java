package com.example.formationcours;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AcnhAPI {
    @GET("villagers/")
    Call<RestAcnhAPIResponse> getAcnhResponse();
}
