package com.richbasoft.ollie.common.firebase.dto

data class FirebaseMessageRequestDto(
    val accessToken: String,
    val title: String,
    val body: String,
    val image: String
)

data class FirebaseMessageRequestDtoList(
    val accessToken: List<String>,
    val title: String,
    val body: String,
    val image: String
)