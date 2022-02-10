package one.tunkshif.ankiestrella

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import one.tunkshif.ankiestrella.data.SourceRegistry
import one.tunkshif.ankiestrella.data.source.SpanishDict
import one.tunkshif.ankiestrella.data.source.YoudaoCollins
import one.tunkshif.ankiestrella.di.appModule
import one.tunkshif.ankiestrella.di.dbModule
import one.tunkshif.ankiestrella.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AnkiEstrella : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

//        configureKoin()
//        registerSources()
    }

    private fun configureKoin() {
        startKoin {
            androidLogger()
            androidContext(this@AnkiEstrella)
            modules(appModule, dbModule, serviceModule)
        }
    }

    private fun registerSources() {
        SourceRegistry.apply {
            register(SpanishDict)
            register(YoudaoCollins)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}