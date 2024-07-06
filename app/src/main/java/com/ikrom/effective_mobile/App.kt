package com.ikrom.effective_mobile

import android.app.Application
import com.ikrom.effective_mobile.di.AppComponent
import com.ikrom.effective_mobile.di.DaggerAppComponent
import com.ikrom.feature_tickets.TicketsDepsStore

class App: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        TicketsDepsStore.deps = appComponent
    }
}