package ru.sberleasing.model.event;

import android.os.Handler;
import dagger.Module;
import dagger.Provides;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;


@Module
public final class EventBusModule {

    @Provides
    @Singleton
    Bus provideMainBus(@Named("main") Handler handler) {
        EventBus.builder().throwSubscriberException(true).installDefaultEventBus();
        return new EventBusAdapter(handler, EventBus.getDefault());
    }
}
