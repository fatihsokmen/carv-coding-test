package com.github.fatihsokmen.carv.asynccall

open class APIMock {

    fun send(message: String) {
    }

    open fun onSend(message: String) = println("Sent: $message")
}