package com.ivan_prokofyev.kilograpptest.Data.network.service;


import com.ivan_prokofyev.kilograpptest.Data.model.ResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Shiraha on 11.09.2016.
 */
public interface KilograppService {
    @GET("/songs/api/songs")
    Call<List<ResponseItem>> getListOfSongs();

    @GET("/songs/api/songs")
    Observable<List<ResponseItem>> getObservableListOfSongs();
}
