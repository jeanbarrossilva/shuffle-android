package com.jeanbarrossilva.shuffle.app

import android.app.Application
import com.jeanbarrossilva.shuffle.feature.tuning.TuningModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class ShuffleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        startKoin {
            androidContext(this@ShuffleApplication)
            modules(ShuffleModule(), TuningModule())
        }
    }
}
