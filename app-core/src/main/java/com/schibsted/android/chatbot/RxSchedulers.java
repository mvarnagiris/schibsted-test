package com.schibsted.android.chatbot;

import rx.Scheduler;

public final class RxSchedulers {
    private final Scheduler main;
    private final Scheduler io;

    public RxSchedulers(Scheduler main, Scheduler io) {
        this.main = main;
        this.io = io;
    }

    public Scheduler main() {
        return main;
    }

    public Scheduler io() {
        return io;
    }
}
