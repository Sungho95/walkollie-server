package com.richbasoft.ollie.common.firebase.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import com.richbasoft.ollie.common.firebase.dto.FirebaseMessageRequestDto
import com.richbasoft.ollie.common.firebase.dto.FirebaseMessageRequestDtoList
import com.richbasoft.ollie.common.utils.logger
import org.springframework.stereotype.Component

@Component
class FirebaseService(
    private val firebaseMessaging: FirebaseMessaging,
) {
    val log = logger()

    fun sendNotification(requestDto: FirebaseMessageRequestDto): String {
        val message = createMessage(requestDto)
        return firebaseMessaging.send(message)
    }

    fun sendReminderNotificationList(requestDto: FirebaseMessageRequestDtoList) {
        val messageList = createMessageByAccessTokenList(requestDto)
        val response = firebaseMessaging.sendEach(messageList)
        log.debug("###리마인더 알림 메시지 결과!! --- 성공: ${response.successCount} 실패: ${response.failureCount}")
        log.debug("###리마인더 알림 메시지 결과!! --- 결과: {}", response.responses)
    }

    private fun createMessage(requestDto: FirebaseMessageRequestDto): Message {
        val notification = Notification.builder()
            .setTitle(requestDto.title)
            .setBody(requestDto.body)
            .setImage(requestDto.image)
            .build()

        val message = Message.builder()
            .setToken(requestDto.accessToken)
            .setNotification(notification)
            .build()

        return message
    }

    private fun createMessageByAccessTokenList(requestDto: FirebaseMessageRequestDtoList): List<Message> {
        val notification = Notification.builder()
            .setTitle(requestDto.title)
            .setBody(requestDto.body)
            .setImage(requestDto.image)
            .build()

        val messageList = requestDto.accessToken.map {
            Message.builder()
                .setToken(it)
                .setNotification(notification)
                .build()
        }

        return messageList
    }
}