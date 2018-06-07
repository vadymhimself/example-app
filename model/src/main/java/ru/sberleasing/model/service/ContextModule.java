package ru.sberleasing.model.service;

import android.content.Context;
import android.os.Handler;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    @Named("main")
    Handler provideMainThreadHandler(Context context) {
        return new Handler(context.getMainLooper());
    }
}
