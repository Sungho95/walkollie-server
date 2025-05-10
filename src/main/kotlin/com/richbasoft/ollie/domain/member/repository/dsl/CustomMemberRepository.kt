package com.richbasoft.ollie.domain.member.repository.dsl

interface CustomMemberRepository {
    fun findSendNotificationTokenList(): List<String>
}