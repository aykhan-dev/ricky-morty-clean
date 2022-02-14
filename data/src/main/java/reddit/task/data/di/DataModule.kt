package reddit.task.data.di

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import reddit.task.data.local.characters.CharactersLocalDataSource
import reddit.task.data.local.characters.CharactersLocalDataSourceImpl
import reddit.task.data.remote.CharactersRemoteDataSource
import reddit.task.data.repository.CharactersRepositoryImpl
import reddit.task.data.room.AppDatabase
import reddit.task.domain.repository.CharactersRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(
            if (getProperty<String>("isDebug") == true.toString()) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )
    }

    single {
        val builder = OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())

        builder.build()
    }

    single {
        Json {
            isLenient = true
            encodeDefaults = false
            ignoreUnknownKeys = true
            prettyPrint = true
        }
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(getProperty<String>("host"))
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, getProperty("database-name")
        ).build()
    }

    single<CharactersRemoteDataSource> {
        get<Retrofit>().create(CharactersRemoteDataSource::class.java)
    }

    single<CharactersLocalDataSource> {
        CharactersLocalDataSourceImpl(
            database = get()
        )
    }

    single<CharactersRepository> {
        CharactersRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

}