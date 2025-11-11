package com.telegramplus.app.data.model

import androidx.annotation.DrawableRes

data class MiniApp(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes val iconResId: Int,
    val deepLink: String,
    val category: MiniAppCategory
)

enum class MiniAppCategory {
    FINANCE,
    LIFESTYLE,
    TRAVEL,
    ENTERTAINMENT,
    PRODUCTIVITY,
    UTILITIES
}
