package com.picpay.desafio.android.data

import androidx.room.Room
import com.google.gson.Gson
import com.picpay.desafio.android.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LOG_INTERCEPTOR = "logInterceptor"
const val OKHTTP_CLIENT_BUILDER = "okHttpClientBuilder"
const val RETROFIT_BUILDER = "retrofitBuilder"
const val GSON_INSTANCE = "gsonInstance"
const val GSON_CONVERTER_FACTORY_INSTANCE = "gsonConverterFactoryInstance"
const val WEB_API_BASE_URL = "webApiBaseUrl"
const val DB_INSTANCE = "dbInstance"

val dataModule = module {

    // inject network
    injectDataNetworkDependencies()

    // inject local database
    injectDataLocalDependencies()

}

fun Module.injectDataNetworkDependencies(){

    single(named(WEB_API_BASE_URL)) {
        "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }

    factory(named(GSON_INSTANCE)) {
        Gson()
    }

    single(named(GSON_CONVERTER_FACTORY_INSTANCE)) {
        GsonConverterFactory.create(get<Gson>(named(GSON_INSTANCE)))
    }

    single(named(LOG_INTERCEPTOR)) {
        HttpLoggingInterceptor().apply {
            level = when {
                BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single(named(OKHTTP_CLIENT_BUILDER)) {
        OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(get<HttpLoggingInterceptor>(named(LOG_INTERCEPTOR)))
            .build()
    }

    single(named(RETROFIT_BUILDER)) {
        Retrofit
            .Builder()
            .baseUrl(get<String>(named(WEB_API_BASE_URL)))
            .addConverterFactory(get<GsonConverterFactory>(named(GSON_CONVERTER_FACTORY_INSTANCE)))
            .client(get<OkHttpClient>(named(OKHTTP_CLIENT_BUILDER)))
            .build()
    }
}

fun Module.injectDataLocalDependencies(){

    single {
        get<ContactDb>(
            named(DB_INSTANCE)
        ).contactDao()
    }

    single(named(DB_INSTANCE)) {
        Room.databaseBuilder(
            androidContext(),
            ContactDb::class.java, "PicPayContact"
        ).build()
    }
}