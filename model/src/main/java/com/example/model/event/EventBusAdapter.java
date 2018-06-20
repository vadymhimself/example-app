package com.example.model.event;

import android.os.Handler;
import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;

class EventBusAdapter implements Bus {

    private final Executor executor;
    private final EventBus bus;

    EventBusAdapter(Executor executor, EventBus bus) {
        this.executor = executor;
        this.bus = bus;
    }

    @Override
    public void register(Object o) {
        bus.register(o);
    }

    @Override
    public void unregister(Object o) {
        bus.unregister(o);
    }

    @Override
    public void post(Object o) {
        executor.execute(() -> bus.post(o));
    }

    @Override
    public boolean isRegistered(Object o) {
        return bus.isRegistered(o);
    }
}
