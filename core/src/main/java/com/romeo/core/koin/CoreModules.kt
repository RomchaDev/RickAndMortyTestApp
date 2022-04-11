package com.romeo.core.koin

import com.romeo.core.data.PrivateConstantsHolder
import com.romeo.core.data.api.ApiService
import com.romeo.core.data.api.MainInterceptor
import com.romeo.core.data.datasource.local.LocalDatasource
import com.romeo.core.data.datasource.local.LocalDatasourceImpl
import com.romeo.core.data.datasource.remote.CharacterRemoteDatasource
import com.romeo.core.data.datasource.remote.RemoteDataSourceImpl
import com.romeo.core.data.datasource.remote.RemoteDatasource
import com.romeo.core.data.datasource.remote.SingUpSingInDatasource
import com.romeo.core.data.repository.CharacterRepository
import com.romeo.core.data.repository.CharacterRepositoryImpl
import com.romeo.core.data.repository.TokenRepository
import com.romeo.core.data.repository.TokenRepositoryImpl
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory

val coreModule = module {
    factory<RemoteDatasource> { RemoteDataSourceImpl(get()) }

    factory<SingUpSingInDatasource> { get<RemoteDatasource>() }
    factory<CharacterRemoteDatasource> { get<RemoteDatasource>() }

    single<LocalDatasource> { LocalDatasourceImpl(get()) }

    factory<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
    factory<TokenRepository> {
        TokenRepositoryImpl(
            get(),
            get(StringQualifier(PrivateConstantsHolder.TOKEN_KEY_QUALIFIER))
        )
    }

    factory { PrivateConstantsHolder() }
}

val constantsModule = module {
    factory(StringQualifier(PrivateConstantsHolder.TOKEN_KEY_QUALIFIER)) {
        get<PrivateConstantsHolder>().tokenKey
    }
}

val apiModule = module {
    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(MainInterceptor(get()))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        Retrofit.Builder()
            .baseUrl(ApiService.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .client(client)
            .build().create(ApiService::class.java)
    }
}

val realmModule = module {
    single {
        RealmConfiguration.Builder()
            .schemaVersion(DB_VERSION)
            .build()
    }
}

private const val DB_VERSION = 1L