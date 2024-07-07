package com.ikrom.effective_mobile.di

import android.app.Application
import com.ikrom.data.Repository
import com.ikrom.data.di.ArtsitService
import com.ikrom.tickets.TicketsDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent: com.ikrom.tickets.TicketsDeps {
    override val repository: Repository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}

@Module
class AppModule {
    @Provides
    @AppScope
    fun provideArtistService() = ArtsitService()
}

@Scope
annotation class AppScope