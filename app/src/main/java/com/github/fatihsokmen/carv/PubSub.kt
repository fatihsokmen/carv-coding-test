package com.github.fatihsokmen.carv

interface PubSubInteractor {
    fun subscribe(subscriber: Subscriber)
    fun publish()
}


class PubSubInteractorImpl : PubSubInteractor {

    private val subscribers = mutableListOf<Subscriber>()

    override fun subscribe(subscriber: Subscriber) {
        subscribers += subscriber
    }

    override fun publish() {

        subscribers.forEach {
            it.onEvent(PubSubEvent.ComplexEvent("text-data"))
        }
    }
}

interface Subscriber {
    fun onEvent(event: PubSubEvent)
}

sealed class PubSubEvent {

    object NoDataEvent : PubSubEvent()
    data class SimpleEvent(val data: Float) : PubSubEvent()
    data class ComplexEvent<T>(val data: T) : PubSubEvent()
}