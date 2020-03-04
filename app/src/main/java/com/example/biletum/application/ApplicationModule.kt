package com.example.biletum.application
import android.content.Context
import android.content.SharedPreferences
import com.example.biletum.BuildConfig
import com.example.biletum.api.BiletumAPI
import com.example.biletum.data.DataRepository
import com.example.biletum.data.local.db.mappers.UserEntityMapper
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.LoggingInterceptor


import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import javax.inject.Singleton

@Module
class ApplicationModule {

    private companion object {
        const val preferences_key = "@preferences_key@"
        const val URL = "https://dev.biletum.com:8900/"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(com.ihsanbal.logging.Level.BASIC)
            .log(Platform.INFO)
            .tag("NETWORK")
            .executor(Executors.newSingleThreadExecutor())
            .build()
        val stethoInterceptor = StethoInterceptor()
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        okHttpClientBuilder.addNetworkInterceptor(stethoInterceptor)

        okHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): BiletumAPI {
        val gson = GsonBuilder().create()
       val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
           .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(BiletumAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDataRepository(knoxxAPI: BiletumAPI): DataRepository {
        return DataRepository(knoxxAPI)
    }


    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(preferences_key, Context.MODE_PRIVATE)


}