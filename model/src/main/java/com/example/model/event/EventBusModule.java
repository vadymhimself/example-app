package com.example.model.event;

import android.os.Handler;
import dagger.Module;
import dagger.Provides;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.Executor;


@Module
public final class EventBusModule {

    @Provides
    @Singleton
    Bus provideMainBus(@Named("mainThread") Executor executor) {
        EventBus.builder().throwSubscriberException(true).installDefaultEventBus();
        return new EventBusAdapter(executor, EventBus.getDefault());
    }
}
