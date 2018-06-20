package com.example.model.persistance

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

import javax.inject.Named
import javax.inject.Singleton

@Module
class PreferencesModule {

    private val KEY = "prefs.sda4t2hjkrw_"
    private val KEY_CREDENTIALS = "Fegs3rv4udor6tv7ap9ew-]P}<KV"

    @Provides
    @Singleton
    internal fun provideAppPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(KEY, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    @Named("credentials")
    internal fun provideCredentialPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(KEY_CREDENTIALS, Context.MODE_PRIVATE)
    }
}
