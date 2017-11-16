package com.mm.product.client.route.utills.rx.schedulers;

import io.reactivex.Scheduler;

/**
 * Created by anup.gupta on 11/15/2017.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
