package com.sri.pokedex.core.network.di

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.sri.pokedex.core.network.BuildConfig
import com.sri.pokedex.core.network.service.PokedexClient
import com.sri.pokedex.core.network.service.PokedexService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule{

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .apply {
                if(BuildConfig.DEBUG) {
                    this.addNetworkInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        },
                    )
                }
            }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun ProvidePokedexService(retrofit: Retrofit): PokedexService{
        return retrofit.create(PokedexService:: class.java)
    }

    @Provides
    @Singleton
    fun providePodexClient(pokedexService: PokedexService): PokedexClient {
        return PokedexClient(pokedexService)
    }
}