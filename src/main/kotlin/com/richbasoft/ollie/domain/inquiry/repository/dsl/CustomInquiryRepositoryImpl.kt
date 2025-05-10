package com.richbasoft.ollie.domain.inquiry.repository.dsl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.domain.inquiry.dto.response.AnswerInfoDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDto
import com.richbasoft.ollie.domain.inquiry.dto.response.QAnswerInfoDto
import com.richbasoft.ollie.domain.inquiry.dto.response.QInquiryInfoDto
import com.richbasoft.ollie.domain.inquiry.entity.Inquiry
import com.richbasoft.ollie.domain.inquiry.entity.QAnswer.answer
import com.richbasoft.ollie.domain.inquiry.entity.QInquiry.inquiry
import com.richbasoft.ollie.domain.ollie.entity.QOllie.ollie
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class CustomInquiryRepositoryImpl(
    private val query: JPAQueryFactory
) : QuerydslRepositorySupport(Inquiry::class.java), CustomInquiryRepository {
    override fun findInquiryList(memberId: Long): List<InquiryInfoDto> {
        val findInquiryInfoList = query
            .select(
                QInquiryInfoDto(
                    inquiry.id,
                    ollie.id,
                    ollie.name,
                    inquiry.title,
                    inquiry.isAnswered,
                    inquiry.createdAt,
                    inquiry.modifiedAt
                )
            )
            .from(inquiry)
            .innerJoin(inquiry.ollie, ollie)
            .where(
                ollie.id.eq(memberId),
                inquiry.isDeleted.eq(false),
            )
            .orderBy(inquiry.createdAt.desc())
            .fetch()

        return findInquiryInfoList
    }

    override fun findInquiryAnswer(inquiryId: Long): AnswerInfoDto {
        val findInquiryAnswerInfo = query
            .select(
                QAnswerInfoDto(
                    answer.id,
                    inquiry.id,
                    answer.admin.name,
                    answer.content,
                    answer.image,
                    answer.createdAt,
                    answer.modifiedAt
                )
            )
            .from(inquiry)
            .innerJoin(answer)
            .on(inquiry.id.eq(answer.inquiry.id))
            .where(
                inquiry.id.eq(inquiryId)
            )
            .fetchOne()

        return findInquiryAnswerInfo!!
    }
}