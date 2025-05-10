package com.richbasoft.ollie.domain.member.utils

import com.richbasoft.ollie.domain.member.entity.Member
import com.richbasoft.ollie.domain.member.repository.MemberRepository
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.common.utils.logger
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class MemberValidationUtils(

    private val memberRepository: MemberRepository

) {
    val log = logger()

    fun getVerifiedMember(memberId: Long): Member {
        return memberRepository.findByIdOrNull(memberId)
            ?: throw BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
    }

    fun verifyExistsEmail(email: String) {
        memberRepository.findByEmail(email)?.let {
            throw BusinessLogicException(ExceptionCode.MEMBER_EXISTS)
        }
    }

    fun verifyExistsDeviceId(deviceId: String) {
        memberRepository.findByEmail(deviceId)?.let {
            throw BusinessLogicException(ExceptionCode.MEMBER_EXISTS)
        }
    }

    fun verifyMemberIdentity(memberId: Long, loginMemberId: Long) {
        if (memberId != loginMemberId) {
            log.info("### 회원 정보 불일치 : request={} expected={}", memberId, loginMemberId)
            throw BusinessLogicException(ExceptionCode.MEMBER_IDENTITY_MISMATCH)
        }
    }
}