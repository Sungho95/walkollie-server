package com.richbasoft.ollie.admin.domain.inquiry.repository.dsl

import com.richbasoft.ollie.admin.domain.common.enums.Answered
import com.richbasoft.ollie.admin.domain.common.enums.Order
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDetailDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDto

interface CustomAdminInquiryRepository {
    fun findAdminInquiryList(condition: Answered, sort: Order): List<AdminInquiryInfoDto>

    fun findAdminInquiry(inquiryId: Long): AdminInquiryInfoDetailDto?
}