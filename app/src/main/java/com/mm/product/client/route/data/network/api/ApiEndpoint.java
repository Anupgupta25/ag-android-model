package com.mm.product.client.route.data.network.api;

import com.mm.product.client.route.data.model.RouteLists;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public interface ApiEndpoint {
    String BASE_URL = "http://www.mocky.io";

    @GET("/v2/5808f00d10000005074c6340")
    Observable<RouteLists> getRouteList();
}
