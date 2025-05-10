package com.richbasoft.ollie.domain.member.repository

import com.richbasoft.ollie.domain.member.entity.Member
import com.richbasoft.ollie.domain.member.repository.dsl.CustomMemberRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long>, CustomMemberRepository {
    fun findByEmail(email: String): Member?

    fun findByDeviceId(deviceId: String): Member?
}