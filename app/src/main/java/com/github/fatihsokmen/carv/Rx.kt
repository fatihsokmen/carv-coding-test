package com.github.fatihsokmen.carv

import android.annotation.SuppressLint
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class Sensors {

    @SuppressLint("CheckResult")
    fun checkConnectivity() {
        // First items also waits 4.5 sec so this is not a complete solution
        // Need more time to work on this
        Observable.interval(0, 1, TimeUnit.SECONDS)
            .map {
                when {
                    it.rem(15) == 0L -> true
                    it.rem(5) == 0L -> true
                    else -> it.rem(3) == 0L
                }
            }
            .debounce(4500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
    }

}
