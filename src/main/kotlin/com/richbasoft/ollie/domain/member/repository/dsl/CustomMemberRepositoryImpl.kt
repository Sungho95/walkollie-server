package com.richbasoft.ollie.domain.member.repository.dsl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.domain.member.entity.Member
import com.richbasoft.ollie.domain.member.entity.QMember.member
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class CustomMemberRepositoryImpl(
    private val query: JPAQueryFactory
) : QuerydslRepositorySupport(Member::class.java), CustomMemberRepository {
    override fun findSendNotificationTokenList(): List<String> {
        val notificationTokenList = query
            .select(
                member.notificationConsent.accessToken
            )
            .from(member)
            .where(
                member.notificationConsent.pushNotification.eq(true),
                member.notificationConsent.accessToken.isNotEmpty,
                member.notificationConsent.accessToken.isNotNull
            )
            .fetch()

        return notificationTokenList
    }
}