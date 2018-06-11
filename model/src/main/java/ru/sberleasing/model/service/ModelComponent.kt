package ru.sberleasing.model.service

import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Component
import ru.sberleasing.model.analytics.Analytics
import ru.sberleasing.model.analytics.AnalyticsModule
import ru.sberleasing.model.api.Api
import ru.sberleasing.model.api.ApiModule
import ru.sberleasing.model.event.Bus
import ru.sberleasing.model.event.EventBusModule
import ru.sberleasing.model.networking.GsonModule
import ru.sberleasing.model.persistance.DatabaseModule
import ru.sberleasing.model.persistance.PreferencesModule

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
