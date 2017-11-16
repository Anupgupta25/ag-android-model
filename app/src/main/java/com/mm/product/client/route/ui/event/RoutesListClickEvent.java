package com.mm.product.client.route.ui.event;

/**
 * Created by anup.gupta on 11/16/2017.
 */

public class RoutesListClickEvent {

    private long id;

    public RoutesListClickEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
