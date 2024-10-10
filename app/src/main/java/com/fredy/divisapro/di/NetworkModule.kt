package com.fredy.divisapro.di

import com.fredy.divisapro.network.ApiService
import com.fredy.divisapro.network.ExchangeRatesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("loginRetrofit")
    fun provideRetrofitForLogin(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/")  // URL para login
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginApiService(@Named("loginRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("currencyRetrofit")
    fun provideRetrofitForCurrency(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.apilayer.com/currency_data/")  // URL para conversión de divisas
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeRatesApiService(@Named("currencyRetrofit") retrofit: Retrofit): ExchangeRatesApi {
        return retrofit.create(ExchangeRatesApi::class.java)
    }
}