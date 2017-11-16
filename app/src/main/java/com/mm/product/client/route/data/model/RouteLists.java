package com.mm.product.client.route.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public class RouteLists {

    @SerializedName("routes")
    @Expose
    private List<Routes> routes = null;

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }

}
