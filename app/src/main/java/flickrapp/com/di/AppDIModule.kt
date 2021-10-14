package flickrapp.com.di

import com.google.gson.Gson
import flickrapp.com.BuildConfig
import flickrapp.com.data.api.FlickrApiService
import flickrapp.com.data.api.deserializers.DateTimeDeserializer
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

val appDIModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    single {
        Gson().newBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, DateTimeDeserializer())
            .create()
    }

    factory {
        GsonConverterFactory.create(get())
    }

    // ApiService

    single {
        get<Retrofit>().create(FlickrApiService::class.java)
    }
}
