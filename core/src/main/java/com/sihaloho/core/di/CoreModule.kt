package com.sihaloho.core.di

import androidx.room.Room
import com.sihaloho.core.data.TeamRepository
import com.sihaloho.core.data.source.local.LocalDataSource
import com.sihaloho.core.data.source.local.room.TeamDatabase
import com.sihaloho.core.data.source.remote.RemoteDataSource
import com.sihaloho.core.data.source.remote.network.ApiService
import com.sihaloho.core.domain.repository.ITeamRepository
import com.sihaloho.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<TeamDatabase>().teamDao() }
    single {
        val passphrase : ByteArray = SQLiteDatabase.getBytes("sihaloho".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
                androidContext(),
                TeamDatabase::class.java, "Team.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {

        val hostname = "thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/34DD923BC56F234ED8F448")
                .add(hostname, "sha256/A4AE2C15A249533B1285C1")
                .add(hostname, "sha256/3D3F3772BF7131B008C2")
                .build()
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build()
    }
    single {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITeamRepository> { TeamRepository(get(), get(), get()) }
}