package reddit.task.initializers

import android.app.Application

interface InitializerBundle {
    fun initAll(application: Application)
}