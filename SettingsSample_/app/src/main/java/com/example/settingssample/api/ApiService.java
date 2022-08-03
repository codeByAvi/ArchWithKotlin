package com.example.settingssample.api;

import com.example.settingssample.model.Root;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("public/v2/comments")
    Observable<List<Root>> getData();
}
