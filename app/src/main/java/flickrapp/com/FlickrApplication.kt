package flickrapp.com

import android.app.Application
import flickrapp.com.di.appDIModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class FlickrApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@FlickrApplication)
            modules(appDIModule)
        }
    }
}