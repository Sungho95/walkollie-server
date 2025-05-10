package com.richbasoft.ollie.admin.domain.inquiry.service

import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerInfoDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerPostDto

interface AdminInquiryCreateService {
    fun createAnswer(inquiryId: Long, adminAnswerPostDto: AdminAnswerPostDto, memberId: Long): AdminAnswerInfoDto
}