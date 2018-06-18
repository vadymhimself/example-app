package ru.sberleasing.model.api

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallbackAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallbackAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sberleasing.model.BuildConfig

import javax.inject.Singleton
import java.util.concurrent.TimeUnit

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
                                 baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallbackAdapterFactory(CoroutineCallbackAdapterFactory())
                .build()
    }


    @Provides
    @IntoSet
    internal fun provideCommonFieldsInterceptor(): Interceptor {
        return Interceptor {
            chain ->
            // common params interceptor
            val req = chain.request()
                    .newBuilder()
                    .url(chain.request().url().newBuilder()
                            .addQueryParameter("units", "metric")
                            .addQueryParameter("APPID", "dc0c3fa551e8e65d822266c250d304ef")
                            .build())
                    .addHeader("Accept", "application/json")
                    .build()
            chain.proceed(req)
        }
    }

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}
