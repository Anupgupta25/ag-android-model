package com.mm.product.client.route.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by anup.gupta on 11/15/2017.
 */
@Parcel
public class Stop {

    @SerializedName("name")
    @Expose
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
