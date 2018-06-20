package com.example.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.*;
import android.support.v4.content.ContextCompat;
import com.squareup.leakcanary.LeakCanary;
import com.example.model.analytics.Analytics;
import com.example.model.event.Bus;
import com.example.model.service.ContextModule;
import com.example.model.service.DaggerModelComponent;

import javax.inject.Inject;

/**
 * Created by Vadym Ovcharenko
 * 27.10.2016.
 */

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static App context;

    private static AppComponent injector;

    @Inject Bus bus;
    @Inject Analytics analytics;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);

        context = this;

        injector = DaggerAppComponent.builder()
                .modelComponent(DaggerModelComponent.builder()
                        .contextModule(new ContextModule(this))
                        .build())
                .build();

        injector.inject(this);
        analytics.init(this);
    }

    public static Context getContext() {
        return context;
    }

    public static AppComponent require() {
        return injector;
    }

    public static Bus getBus() {
        return context.bus;
    }

    public static String getStr(@StringRes int id) {
        return context.getString(id);
    }

    public static String getStr(@StringRes int resId, Object... formatArgs) {
        return context.getString(resId, formatArgs);
    }

    public static String getStr(@PluralsRes int id, int quantity) {
        return context.getResources().getQuantityString(id, quantity);
    }

    public static int getDimen(@DimenRes int dimenRes) {
        return App.getContext().getResources()
                .getDimensionPixelOffset(dimenRes);
    }

    @ColorInt
    public static int getCol(@ColorRes int sberGreen) {
        return ContextCompat.getColor(context, sberGreen);
    }
}
