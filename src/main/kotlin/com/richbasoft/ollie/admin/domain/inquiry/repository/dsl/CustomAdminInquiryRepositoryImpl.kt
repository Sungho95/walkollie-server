package com.richbasoft.ollie.admin.domain.inquiry.repository.dsl

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.admin.domain.common.enums.Answered
import com.richbasoft.ollie.admin.domain.common.enums.Order
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDetailDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.QAdminInquiryInfoDetailDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.QAdminInquiryInfoDto
import com.richbasoft.ollie.domain.inquiry.entity.Inquiry
import com.richbasoft.ollie.domain.inquiry.entity.QInquiry.inquiry
import com.richbasoft.ollie.domain.ollie.entity.QOllie.ollie
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class CustomAdminInquiryRepositoryImpl(
    private val query: JPAQueryFactory
) : QuerydslRepositorySupport(Inquiry::class.java), CustomAdminInquiryRepository {
    override fun findAdminInquiryList(condition: Answered, sort: Order): List<AdminInquiryInfoDto> {
        val inquiryInfoList = query
            .select(
                QAdminInquiryInfoDto(
                    inquiry.id,
                    inquiry.ollie.id,
                    inquiry.ollie.name,
                    inquiry.title,
                    inquiry.isAnswered,
                    inquiry.createdAt,
                    inquiry.modifiedAt
                )
            )
            .from(inquiry)
            .innerJoin(inquiry.ollie, ollie)
            .where(
                isAnsweredConditionEq(condition)
            )
            .orderBy(sortCondition(sort))
            .fetch()

        return inquiryInfoList
    }

    override fun findAdminInquiry(inquiryId: Long): AdminInquiryInfoDetailDto? {
        val inquiryDetailInfo = query
            .select(
                QAdminInquiryInfoDetailDto(
                    inquiry.id,
                    inquiry.ollie.id,
                    inquiry.ollie.name,
                    inquiry.title,
                    inquiry.content,
                    inquiry.image,
                    inquiry.isAnswered,
                    inquiry.createdAt,
                    inquiry.modifiedAt
                )
            )
            .from(inquiry)
            .innerJoin(inquiry.ollie, ollie)
            .where(
                inquiry.id.eq(inquiryId)
            )
            .fetchOne()

        return inquiryDetailInfo
    }

    private fun isAnsweredConditionEq(condition: Answered): BooleanExpression? {
        return when (condition) {
            Answered.ALL -> null
            Answered.TRUE -> inquiry.isAnswered.eq(true)
            Answered.FALSE -> inquiry.isAnswered.eq(false)
        }
    }

    private fun sortCondition(sort: Order): OrderSpecifier<*>? {
        return when (sort) {
            Order.ASC -> inquiry.createdAt.asc()
            Order.DESC -> inquiry.createdAt.desc()
        }
    }
}