package reddit.task

import android.app.Application
import org.koin.android.BuildConfig
import reddit.task.di.appComponent
import reddit.task.initializers.InitializerBundle
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    private val initializer by inject<InitializerBundle>()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(androidContext = this@App)
            properties(
                mapOf(
                    "isDebug" to BuildConfig.DEBUG.toString(),
                    "database-name" to "APP_DATABASE",
                    "host" to "https://rickandmortyapi.com/api/"
                )
            )
            modules(appComponent)
        }

        initializer.initAll(this)
    }

}