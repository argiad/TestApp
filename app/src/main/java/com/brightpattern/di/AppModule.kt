package com.brightpattern.di

import com.brightpattern.common.Constants
import com.brightpattern.data.remote.NewYorkTimesAPI
import com.brightpattern.data.repository.NYTRepoImpl
import com.brightpattern.domain.repository.NYTRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}