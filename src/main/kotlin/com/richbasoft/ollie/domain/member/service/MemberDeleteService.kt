package com.richbasoft.ollie.domain.member.service

import com.richbasoft.ollie.domain.member.repository.MemberRepository
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberDeleteService(

    private val memberRepository: MemberRepository,
    private val memberValidationUtils: MemberValidationUtils

) {
    fun deleteMember(memberId: Long, loginMemberId: Long) {
        memberValidationUtils.verifyMemberIdentity(memberId, loginMemberId)

        val findMember = memberValidationUtils.getVerifiedMember(memberId)
        findMember.setStatusWithdrawal()

        memberRepository.delete(findMember)
    }
}