package com.richbasoft.ollie.admin.domain.inquiry.service

import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDetailDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDto

interface AdminInquiryReadService {
    fun getAdminInquiryList(condition: String, sort: String): List<AdminInquiryInfoDto>

    fun getAdminInquiry(inquiryId: Long): AdminInquiryInfoDetailDto
}