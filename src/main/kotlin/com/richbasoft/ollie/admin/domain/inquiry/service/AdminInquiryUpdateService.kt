package com.richbasoft.ollie.admin.domain.inquiry.service

import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerInfoDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerPatchDto

interface AdminInquiryUpdateService {
    fun updateAnswer(inquiryId: Long, answerId: Long, answerPatchDto: AdminAnswerPatchDto): AdminAnswerInfoDto
}