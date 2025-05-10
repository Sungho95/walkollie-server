package com.richbasoft.ollie.domain.notification.service

import com.richbasoft.ollie.common.firebase.dto.FirebaseMessageRequestDto
import com.richbasoft.ollie.common.firebase.service.FirebaseService
import com.richbasoft.ollie.common.utils.logger
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import com.richbasoft.ollie.domain.notification.dto.NotificationPostDto
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val firebaseService: FirebaseService,
    private val memberValidationUtils: MemberValidationUtils
) {
    val log = logger()

    fun sendNotification(notificationPostDto: NotificationPostDto) {
        val findMember = memberValidationUtils.getVerifiedMember(notificationPostDto.receiverId)
        val pushNotification = findMember.notificationConsent.pushNotification
        val accessToken = findMember.notificationConsent.accessToken

        if (pushNotification && accessToken.isNotEmpty()) {
            val request = FirebaseMessageRequestDto(
                accessToken = accessToken,
                title = notificationPostDto.title,
                body = notificationPostDto.body,
                image = notificationPostDto.image
            )

            val sendNotification = firebaseService.sendNotification(request)
            log.info("Notification sent: $sendNotification")
        }
    }
}