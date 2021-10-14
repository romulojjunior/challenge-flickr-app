package flickrapp.com

import android.app.Application
import flickrapp.com.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class FlickrApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@FlickrApplication)
            modules(
                appDIModule,
                viewModelsDIModule,
                repositoriesDIModule,
                usecasesDIModule,
                apiServicesDIModule
            )
        }
    }
}
