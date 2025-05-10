package com.richbasoft.ollie.domain.inquiry.service

import com.richbasoft.ollie.domain.inquiry.dto.request.InquiryPostDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDetailDto

interface InquiryCreateService {
    fun createInquiry(inquiryPostDto: InquiryPostDto, memberId: Long): InquiryInfoDetailDto
}