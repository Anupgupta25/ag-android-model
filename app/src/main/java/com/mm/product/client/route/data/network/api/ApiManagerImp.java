package com.mm.product.client.route.data.network.api;

import com.mm.product.client.route.data.model.RouteLists;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public class ApiManagerImp implements ApiManager {

    private ApiEndpoint retrofitService;

    @Inject
    public ApiManagerImp(Retrofit retrofit) {
        this.retrofitService = retrofit.create(ApiEndpoint.class);
    }

    @Override
    public Observable<RouteLists> getRouteList() {
        return retrofitService.getRouteList();
    }
}
