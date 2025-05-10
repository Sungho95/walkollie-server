package com.richbasoft.ollie.domain.inquiry.service

import com.richbasoft.ollie.domain.inquiry.dto.response.AnswerInfoDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDetailDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDto

interface InquiryReadService {
    fun getInquiryList(memberId: Long): List<InquiryInfoDto>
    fun getInquiry(inquiryId: Long, memberId: Long): InquiryInfoDetailDto
    fun getInquiryAnswer(inquiryId: Long, memberId: Long): AnswerInfoDto
}