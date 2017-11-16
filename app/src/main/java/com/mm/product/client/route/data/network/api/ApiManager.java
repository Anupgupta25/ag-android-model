package com.mm.product.client.route.data.network.api;

import com.mm.product.client.route.data.model.RouteLists;

import io.reactivex.Observable;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public interface ApiManager {

    Observable<RouteLists> getRouteList();

}
