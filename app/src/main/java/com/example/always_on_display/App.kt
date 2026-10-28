package com.example.always_on_display

import android.app.Application
import com.example.always_on_display.di.AppContainer

class App : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}
