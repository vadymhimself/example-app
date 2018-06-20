package com.example.model.service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.Executor;

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
    @Named("mainThread")
    Executor provideMainThreadExecutor(Context context) {
        return new LooperExecutor(context.getMainLooper());
    }

    public final class LooperExecutor implements Executor {

        private final Handler handler;

        LooperExecutor(Looper looper) {
            this.handler = new Handler(looper);
        }

        @Override
        public void execute(@NonNull Runnable command) {
            handler.post(command);
        }
    }

}
