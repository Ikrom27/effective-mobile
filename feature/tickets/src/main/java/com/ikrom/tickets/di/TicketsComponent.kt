package com.ikrom.tickets.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.ikrom.data.Repository
import com.ikrom.tickets.TicketsFragment
import dagger.Component
import kotlin.properties.Delegates.notNull


@Component(dependencies = [TicketsDeps::class])
interface TicketsComponent{
    fun inject(fragment: TicketsFragment)

    @Component.Builder
    interface Builder {
        fun deps(ticketsDeps: TicketsDeps) : Builder
        fun build(): TicketsComponent
    }
}

interface TicketsDeps{
    val repository: Repository
}

interface TicketsDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: TicketsDeps

    companion object : TicketsDepsProvider by TicketsDepsStore
}

object TicketsDepsStore : TicketsDepsProvider {
    override var deps: TicketsDeps by notNull()
}

internal class TicketsComponentViewModel : ViewModel() {

    val ticketsComponent =
        DaggerTicketsComponent.builder().deps(TicketsDepsProvider.deps).build()
}


