package com.example.model.analytics;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.google.gson.Gson;
import io.fabric.sdk.android.Fabric;
import com.example.model.BuildConfig;

public class AnalyticsAdapter implements Analytics {

    private final Gson gson;

    AnalyticsAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void setUserIdentifier(String userIdentifier) {
        Crashlytics.setUserIdentifier(userIdentifier);
    }

    @Override
    public void logNotFatal(Throwable throwable) {
        Crashlytics.logException(throwable);
    }

    @Override
    public void logEvent(Event event) {
        //TODO
    }

    @Override
    public void init(Application app) {
        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder()
                        .disabled(BuildConfig.DEBUG)
                        .build())
                .build();

        Fabric.with(app, crashlyticsKit);
    }
}
