package com.example.model.service

import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Component
import com.example.model.analytics.Analytics
import com.example.model.analytics.AnalyticsModule
import com.example.model.api.Api
import com.example.model.api.ApiModule
import com.example.model.event.Bus
import com.example.model.event.EventBusModule
import com.example.model.networking.GsonModule
import com.example.model.persistance.DatabaseModule
import com.example.model.persistance.PreferencesModule

import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class,
    EventBusModule::class,
    DatabaseModule::class,
    GsonModule::class,
    PreferencesModule::class,
    AnalyticsModule::class,
    ApiModule::class
])
interface ModelComponent {
    fun exposeBus(): Bus
    fun exposePreferences(): SharedPreferences
    fun exposeGson(): Gson
    fun exposeAnalytics(): Analytics
    fun exposeApi(): Api
}
