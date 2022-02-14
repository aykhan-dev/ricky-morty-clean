package reddit.task.initializers

import android.app.Application

class AppInitializers(private vararg val initializers: AppInitializer) : InitializerBundle {

    override fun initAll(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }

}