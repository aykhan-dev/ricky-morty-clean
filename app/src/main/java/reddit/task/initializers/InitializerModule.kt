package reddit.task.initializers

import org.koin.dsl.module

val initModule = module {

    single<InitializerBundle> {
        AppInitializers(
            TimberInitializer(isDebug = getProperty<String>("isDebug") == true.toString()),
        )
    }

}