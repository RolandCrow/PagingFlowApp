package com.example.compose_lifecycle_components_app

import android.os.CountDownTimer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class CustomCountdown(
    private val onTick: ((currentValue: Int) -> Unit),
    private val onFinish: (() -> Unit)
):DefaultLifecycleObserver {
    var timer: IntervalTimer = IntervalTimer(
        onTick,
        onFinish,
        millisInFuture = 60000,
        countDownInterval = 1000
    )

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if(timer.lastKnownTime > 0) {
            timer.cancel()
            timer = IntervalTimer(
                onTick = onTick,
                onFinish = onFinish,
                millisInFuture = timer.lastKnownTime,
                countDownInterval = 1000
            )
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        timer.cancel()
    }

    fun stop() = timer.cancel()

    class IntervalTimer(
        private val onTick: ((currentValue: Int)-> Unit),
        private val onFinish: (() -> Unit),
        millisInFuture: Long,
        countDownInterval: Long,
    ): CountDownTimer(millisInFuture,countDownInterval) {
        var lastKnownTime:Long = millisInFuture

        init {
            this.start()
        }

        override fun onFinish() {
            lastKnownTime = 0
            onFinish.invoke()
        }

        override fun onTick(p0: Long) {
            lastKnownTime = p0
            onTick(p0.toInt())
        }
    }
}