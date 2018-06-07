package ru.sberleasing.model.event;

import android.os.Handler;
import org.greenrobot.eventbus.EventBus;

class EventBusAdapter implements Bus {

    private final Handler handler;
    private final EventBus bus;

    EventBusAdapter(Handler handler, EventBus bus) {
        this.handler = handler;
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
        handler.post(() -> bus.post(o));
    }

    @Override
    public boolean isRegistered(Object o) {
        return bus.isRegistered(o);
    }
}
