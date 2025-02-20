package com.ailiwean.core.helper

import android.annotation.SuppressLint
import com.ailiwean.core.Config
import com.ailiwean.core.OnGestureListener
import com.google.android.cameraview.BaseCameraView

/**
 * @Package:        com.ailiwean.core.helper
 * @ClassName:      ZoomHelper
 * @Description:    变焦Helper
 * @Author:         SWY
 * @CreateDate:     2020/4/19 1:45 AM
 */
object ZoomHelper {

    var currentPercent: Float = 0f

    @SuppressLint("ClickableViewAccessibility")
    fun toAutoZoom(view: BaseCameraView) {
        Config.currentZoom = 0f
        view.setOnTouchListener(object : OnGestureListener(view.context) {
            override fun onStepFingerChange(total: Float, offset: Float) {
                currentPercent += offset / 500
                view.setZoom(currentPercent.let {
                    when {
                        it > 1f -> 1f
                        it < 0f -> 0f
                        else -> it
                    }
                })
            }

            override fun onDoubleClick() {
                if (currentPercent < 1f)
                    currentPercent = 1f
                else currentPercent = 0f
                view.setZoom(currentPercent)
            }

            override fun onStepEnd() {
            }

        })
    }

    fun close(view: BaseCameraView) {
        view.setOnTouchListener(null)
    }

}