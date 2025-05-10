package com.richbasoft.ollie.interfaces.external.notification

import com.richbasoft.ollie.domain.notification.dto.NotificationPostDto
import com.richbasoft.ollie.domain.notification.service.NotificationService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/notification")
class NotificationController(
    private val notificationService: NotificationService
) {

    @PostMapping("/send")
    fun sendNotification(@Valid @RequestBody notificationPostDto: NotificationPostDto) {
        notificationService.sendNotification(notificationPostDto)
    }
}