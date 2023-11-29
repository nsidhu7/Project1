package com.mobile.newsapp.dependencyin

import com.mobile.newsapp.data.remote.NewsApi
import com.mobile.newsapp.data.remote.NewsApi.Companion.BASE_URL
import com.mobile.newsapp.data.repository.NewsRepositoryImplementation
import com.mobile.newsapp.domain.repository.NewsRepository
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
    @Provides // Provides a singleton instance of NewsApi
    @Singleton
    fun provideNewsApi(): NewsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NewsApi::class.java)
    }

    // Provides a singleton instance of NewsRepository with NewsApi dependency
    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImplementation(newsApi)
    }
}