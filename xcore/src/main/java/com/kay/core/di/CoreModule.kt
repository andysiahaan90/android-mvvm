package com.kay.core.di

import android.app.Application
import android.content.SharedPreferences
import com.kay.core.enviroments.inject
import com.kay.core.extension.PrefHelper
import com.kay.core.network.AccessTokenInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideSharedPrefs(application: Application): SharedPreferences{
        return PrefHelper.defaultPrefs(application)
    }


    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10L * 1024 * 1024 // 10MB of network cache
        return Cache(application.cacheDir, cacheSize)
    }




    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, prefs: SharedPreferences): OkHttpClient =
            OkHttpClient.Builder().apply {
                cache(cache)
                addInterceptor(AccessTokenInterceptor(prefs))
                inject()
            }.build()

    @Provides
    @Singleton
    fun provideRetrofit(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build()
}