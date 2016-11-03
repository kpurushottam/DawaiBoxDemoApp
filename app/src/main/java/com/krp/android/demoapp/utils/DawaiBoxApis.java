package com.krp.android.demoapp.utils;

import com.krp.android.demoapp.modals.response.GetDrugsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Surati on 11/3/2016.
 */
public interface DawaiBoxApis {

    @GET("searchdrugs")
    Call<GetDrugsApiResponse> getDrugs(@Query("Id") long id,
                                      @Query("SearchText") String searchText,
                                      @Query("start") int start,
                                      @Query("limit") int limit,
                                      @Query("role") String role);

}
