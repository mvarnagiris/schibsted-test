package com.schibsted.android.chatbot;

import static rx.schedulers.Schedulers.immediate;

public class RxSchedulersUtils {
    public static RxSchedulers rxSchedulers() {
        return new RxSchedulers(immediate(), immediate());
    }
}
