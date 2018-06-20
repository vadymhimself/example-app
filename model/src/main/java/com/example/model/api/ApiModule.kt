package com.example.model.api

import com.example.model.BuildConfig
import com.example.model.userdata.UserData
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallbackAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
internal class ApiModule {
    @Provides
    @Singleton
    internal fun provideOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
        // other interceptors
        for (interceptor in interceptors) {
            builder.addInterceptor(interceptor)
        }
        return builder.readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @IntoSet
    internal fun provideBodyLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    internal fun provideBaseUrl(): String {
        return BuildConfig.API_BASE_URL
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson,
                                 baseUrl: String,
                                 @Named("mainThread") executor: Executor): Retrofit {

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallbackAdapterFactory(CoroutineCallbackAdapterFactory(executor))
                .build()
    }


    @Provides
    @IntoSet
    internal fun provideCommonFieldsInterceptor(userData: UserData): Interceptor {
        return Interceptor { chain ->

            var req = chain.request()
            userData.accessToken?.let {
                val url = req.url().newBuilder()
                        .addQueryParameter("access_token", it.accessToken)
                        .build()
                req = req.newBuilder().url(url).build()
            }
             chain.proceed(req)
        }
    }

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(retrofit: Retrofit): NetworkInterface {
        return retrofit.create(NetworkInterface::class.java)
    }
}
