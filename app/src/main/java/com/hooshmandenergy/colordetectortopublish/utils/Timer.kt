package com.hooshmandenergy.colordetectortopublish.utils

import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun CoroutineScope.timer(delayTime: Long, action: suspend TimerScope.() -> Unit): Job {
    return launch {
        val scope = TimerScope()

        while (true) {
            measureTimeMillis {
                try {
                    action(scope)
                } catch (ex: Exception) {
                    Log.e("Timer coroutine bug", ex.toString())
                }
            }

            if (scope.isCanceled) {
                break
            }

            delay(delayTime)

            yield()
        }
    }
}

class TimerScope {
    var isCanceled: Boolean = false
        private set

}
