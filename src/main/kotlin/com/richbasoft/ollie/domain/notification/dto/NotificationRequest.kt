package com.richbasoft.ollie.domain.notification.dto

data class NotificationPostDto(
    val receiverId: Long,
    val title: String,
    val body: String,
    val image: String
)