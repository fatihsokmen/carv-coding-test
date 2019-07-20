package com.github.fatihsokmen.carv.asynccall

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicBoolean

class AsyncCallWrapper : APIMock() {

    private val queue = LinkedBlockingQueue<String>()
    private var blocked = AtomicBoolean(BLOCKED.not())

    fun sendAsync(message: String) {
        queue.offer(message)
        sendInternal()
    }

    private fun sendInternal() {
        if (blocked.getAndSet(BLOCKED).not()) {
            send(queue.poll())
        }
    }

    override fun onSend(message: String) {
        blocked.set(BLOCKED.not())
        sendInternal()
    }

    companion object {
        private const val BLOCKED = true
    }
}