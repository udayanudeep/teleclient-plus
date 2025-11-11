package com.telegramplus.app.data.model

import androidx.annotation.DrawableRes


data class Reward(
    val id: String,
    val title: String,
    val description: String,
    val pointsRequired: Int,
    @DrawableRes val iconResId: Int
)
