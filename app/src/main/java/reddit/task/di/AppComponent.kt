package reddit.task.di

import reddit.task.data.di.dataModule
import reddit.task.domain.di.domainModule
import reddit.task.initializers.initModule
import reddit.task.presentation.di.presentationModule

val appComponent = listOf(
    initModule,
    dataModule,
    domainModule,
    presentationModule
)