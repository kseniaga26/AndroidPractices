package ru.kseniaga.androidpractices.di

import ru.kseniaga.androidpractices.data.api.ListTitleApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideRetrofit() }
    single { provideNetworkApi(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.kinopoisk.dev/v1.4/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()

}

fun provideNetworkApi(retrofit: Retrofit): ListTitleApi =
    retrofit.create(ListTitleApi::class.java)