package ru.sberleasing.model.analytics;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AnalyticsModule {

    @Provides
    @Singleton
    Analytics provideAnalytics(Gson gson) {
        return new AnalyticsAdapter(gson);
    }
}
