package com.richbasoft.ollie.domain.inquiry.repository.dsl

import com.richbasoft.ollie.domain.inquiry.dto.response.AnswerInfoDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDto

interface CustomInquiryRepository {
    fun findInquiryList(memberId: Long): List<InquiryInfoDto>

    fun findInquiryAnswer(inquiryId: Long): AnswerInfoDto
}