package com.richbasoft.ollie.common.scheduler

import com.richbasoft.ollie.common.firebase.dto.FirebaseMessageRequestDtoList
import com.richbasoft.ollie.common.firebase.service.FirebaseService
import com.richbasoft.ollie.common.utils.logger
import com.richbasoft.ollie.domain.member.repository.MemberRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SchedulerService(
    private val memberRepository: MemberRepository,
    private val firebaseService: FirebaseService,
) {

    val log = logger()

    @OptIn(DelicateCoroutinesApi::class)
    @Scheduled(cron = "0 0 20 * * ?")
    fun sendReminderNotification() {
        GlobalScope.launch {
            val notificationTokenList: List<String> = memberRepository.findSendNotificationTokenList()

            val firebaseMessage = FirebaseMessageRequestDtoList(
                accessToken = notificationTokenList,
                title = "제목",
                body = "내용",
                image = ""
            )

            try {
                firebaseService.sendReminderNotificationList(firebaseMessage)
            } catch (e: Exception) {
                log.error("### 푸시 알림 발송 실패!! --- ${e.message}")
            }
        }
    }
}