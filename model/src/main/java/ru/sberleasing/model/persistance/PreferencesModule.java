package ru.sberleasing.model.persistance;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class PreferencesModule {

    private final String KEY = "prefs.sda4t2hjkrw_";
    private final String KEY_CREDENTIALS = "Fegs3rv4udor6tv7ap9ew-]P}<KV";

    @Provides
    @Singleton
    SharedPreferences provideAppPreferences(Context context) {
        return context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @Named("credentials")
    SharedPreferences provideCredentialPreferences(Context context) {
        return context.getSharedPreferences(KEY_CREDENTIALS, Context.MODE_PRIVATE);
    }
}
