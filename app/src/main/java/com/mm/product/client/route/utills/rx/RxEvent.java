package com.mm.product.client.route.utills.rx;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by anup.gupta on 11/16/2017.
 */

public class RxEvent {
    private static volatile RxEvent instance = null;
    private PublishSubject<Object> subject = PublishSubject.create();

    public static RxEvent getInstance() {
        if (instance == null) {
            synchronized (RxEvent.class) {
                if (instance == null) {
                    instance = new RxEvent();
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("all")
    public Observable<Object> getEvents() {
        return subject.filter(o -> o != null);
    }

    public void postEvent(@NonNull Object event) {
        if (subject.hasObservers()) {
            subject.onNext(event);
        }
    }

}

