package com.example.model.analytics;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.example.model.BuildConfig;
import io.fabric.sdk.android.Fabric;

public class AnalyticsAdapter implements Analytics {


    AnalyticsAdapter(Context context) {
        Crashlytics crashlyticsKit = new Crashlytics.Builder()
            .core(new CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build())
            .build();

        Fabric.with(context, crashlyticsKit);
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
}
