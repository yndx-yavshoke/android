package ru.yavshok.app.utils

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.BeforeClass


abstract class DisabledSystemAnimations {

    companion object {
        @JvmStatic
        @BeforeClass
        fun disableSystemAnimations() {
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

            device.executeShellCommand("settings put global transition_animation_scale 0.0")
            device.executeShellCommand("settings put global window_animation_scale 0.0")
            device.executeShellCommand("settings put global animator_duration_scale 0.0")

        }
    }
}
