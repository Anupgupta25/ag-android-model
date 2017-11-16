package com.mm.product.client.route.ui.main;

import com.mm.product.client.route.data.model.RouteLists;
import com.mm.product.client.route.data.model.Routes;
import com.mm.product.client.route.ui.base.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public interface MainView extends BaseView {

    void onRoutesLoaded(RouteLists routes);

    void onRoutesDetailsLoaded(List<Routes> route);
}
