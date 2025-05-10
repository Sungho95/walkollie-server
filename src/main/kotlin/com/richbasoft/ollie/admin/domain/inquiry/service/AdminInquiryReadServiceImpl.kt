package com.richbasoft.ollie.admin.domain.inquiry.service

import com.richbasoft.ollie.admin.domain.common.enums.Answered
import com.richbasoft.ollie.admin.domain.common.enums.Order
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDetailDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDto
import com.richbasoft.ollie.admin.domain.inquiry.repository.AdminInquiryRepository
import com.richbasoft.ollie.common.base.service.BaseService
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.common.utils.validator.toEnum
import org.springframework.stereotype.Service

@Service
class AdminInquiryReadServiceImpl(
    private val adminInquiryRepository: AdminInquiryRepository,
) : AdminInquiryReadService, BaseService() {
    override fun getAdminInquiryList(condition: String, sort: String): List<AdminInquiryInfoDto> {
        val answered = condition.toEnum(Answered::class.java)
        val order = sort.toEnum(Order::class.java)

        val findInquiryInfoList = adminInquiryRepository.findAdminInquiryList(answered, order)

        return findInquiryInfoList
    }

    override fun getAdminInquiry(inquiryId: Long): AdminInquiryInfoDetailDto {
        val findInquiryInfoDetail = adminInquiryRepository.findAdminInquiry(inquiryId)
            ?: throw BusinessLogicException(ExceptionCode.INQUIRY_NOT_FOUND)

        return findInquiryInfoDetail
    }
}