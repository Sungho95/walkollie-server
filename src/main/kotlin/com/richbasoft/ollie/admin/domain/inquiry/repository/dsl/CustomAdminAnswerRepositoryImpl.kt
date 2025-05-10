package com.richbasoft.ollie.admin.domain.inquiry.repository.dsl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.domain.inquiry.entity.Answer
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class CustomAdminAnswerRepositoryImpl(
    private val query: JPAQueryFactory
) : QuerydslRepositorySupport(Answer::class.java), CustomAdminAnswerRepository {
}