package reddit.task.initializers

import android.app.Application
import reddit.task.initializers.AppInitializer
import timber.log.Timber

class TimberInitializer(private val isDebug: Boolean) : AppInitializer {

    override fun init(application: Application) {
        if (isDebug) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement) =
                    "${super.createStackElementTag(element)}:${element.lineNumber}"
            })
        }
    }
}
