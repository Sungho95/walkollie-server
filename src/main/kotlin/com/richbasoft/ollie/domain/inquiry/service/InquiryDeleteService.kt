package com.richbasoft.ollie.domain.inquiry.service

interface InquiryDeleteService {
    fun deleteInquiry(inquiryId: Long, memberId: Long)
}