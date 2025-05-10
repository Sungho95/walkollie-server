package com.richbasoft.ollie.admin.domain.inquiry.service

interface AdminInquiryDeleteService {
    fun deleteAnswer(inquiryId: Long, answerId: Long)
}