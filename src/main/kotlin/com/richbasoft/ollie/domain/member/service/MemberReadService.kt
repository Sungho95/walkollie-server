package com.richbasoft.ollie.domain.member.service

import com.richbasoft.ollie.domain.member.dto.MemberResponseDto
import com.richbasoft.ollie.domain.member.repository.MemberRepository
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import com.richbasoft.ollie.common.utils.logger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberReadService(

    private val memberValidationUtils: MemberValidationUtils,
    private val memberRepository: MemberRepository

) {
    val log = logger()
    fun memberCheck(deviceId: String): Boolean {
        val findMember = memberRepository.findByDeviceId(deviceId)

        return findMember != null
    }

    fun getMemberInfo(memberId: Long, loginMemberId: Long): MemberResponseDto {
        memberValidationUtils.verifyMemberIdentity(memberId, loginMemberId)
        val findMember = memberValidationUtils.getVerifiedMember(memberId)

        return MemberResponseDto.from(findMember)
    }

    fun getMemberInfoList(): List<MemberResponseDto> {
        val memberList = memberRepository.findAll()
        return memberList.map {
            MemberResponseDto.from(it)
        }
    }
}