package com.richbasoft.ollie.domain.member.service

import com.richbasoft.ollie.domain.member.dto.MemberResponseDto
import com.richbasoft.ollie.domain.member.dto.NotificationConsentPatchDto
import com.richbasoft.ollie.domain.member.entity.NotificationConsent
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberUpdateService(

    private val memberValidationUtils: MemberValidationUtils

) {

    fun changeNotificationConsent(
        notificationConsentPatchDto: NotificationConsentPatchDto,
        memberId: Long
    ): MemberResponseDto {
        val findMember = memberValidationUtils.getVerifiedMember(memberId)

        val notificationConsent = NotificationConsent(
            pushNotification = notificationConsentPatchDto.pushNotification,
            marketingNotification = notificationConsentPatchDto.marketingNotification,
            accessToken = notificationConsentPatchDto.accessToken
        )

        findMember.updateNotificationConsent(notificationConsent)

        return MemberResponseDto.from(findMember)
    }
}