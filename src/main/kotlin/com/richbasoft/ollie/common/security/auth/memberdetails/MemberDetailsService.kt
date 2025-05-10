package com.richbasoft.ollie.common.security.auth.memberdetails

import com.richbasoft.ollie.domain.member.repository.MemberRepository
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class MemberDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {

    override fun loadUserByUsername(deviceId: String): UserDetails {
        val findMember = memberRepository.findByDeviceId(deviceId)
            ?: throw BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)

        return MemberDetails.from(findMember)
    }
}