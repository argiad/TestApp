package com.brightpattern.di

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import com.brightpattern.common.Constants
import com.brightpattern.common.Utility
import com.brightpattern.data.remote.NewYorkTimesAPI
import com.brightpattern.data.repository.NYTRepoImpl
import com.brightpattern.domain.repository.NYTRepo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): NewYorkTimesAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewYorkTimesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: NewYorkTimesAPI): NYTRepo {
        return NYTRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideAssets(context: Application): AssetManager {
        return context.assets
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideUtility(@ApplicationContext context: Context): Utility {
        return Utility(context, provideGson())
    }
}