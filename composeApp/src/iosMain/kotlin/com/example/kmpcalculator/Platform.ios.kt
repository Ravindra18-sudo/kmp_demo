package com.example.kmpcalculator

import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        "${UIDevice.currentDevice.systemName()} ${UIDevice.currentDevice.systemVersion}"
    override val isApp: Boolean = true
}

actual fun getPlatform(): Platform = IOSPlatform()
