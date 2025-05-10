package com.richbasoft.ollie.domain.inquiry.service

import com.richbasoft.ollie.domain.inquiry.dto.request.InquiryPatchDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDetailDto

interface InquiryUpdateService {
    fun updateInquiry(inquiryId: Long, inquiryPatchDto: InquiryPatchDto, memberId: Long): InquiryInfoDetailDto
}