package com.example.model.analytics;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AnalyticsModule {

    @Provides
    @Singleton
    Analytics provideAnalytics(Context context) {
        return new AnalyticsAdapter(context);
    }
}
