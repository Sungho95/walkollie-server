package com.richbasoft.ollie.common.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FCMConfig {

    @Bean
    fun firebaseMessaging(): FirebaseMessaging {
        val resource = ClassPathResource("firebase/firebase-key.json")
        val inputStream = resource.inputStream
        val credentials = GoogleCredentials.fromStream(inputStream)

        val firebaseApp = FirebaseApp.getApps()
            .firstOrNull { it.name == FirebaseApp.DEFAULT_APP_NAME }
            ?: run {
                val options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build()
                FirebaseApp.initializeApp(options)
            }

        return FirebaseMessaging.getInstance(firebaseApp)
    }
}