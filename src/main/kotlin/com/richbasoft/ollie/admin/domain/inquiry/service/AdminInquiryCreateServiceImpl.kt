package com.richbasoft.ollie.admin.domain.inquiry.service

import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerInfoDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerPostDto
import com.richbasoft.ollie.admin.domain.inquiry.repository.AdminAnswerRepository
import com.richbasoft.ollie.admin.domain.inquiry.repository.AdminInquiryRepository
import com.richbasoft.ollie.admin.domain.inquiry.utils.AdminInquiryValidationUtils
import com.richbasoft.ollie.common.base.service.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminInquiryCreateServiceImpl(
    private val adminAnswerRepository: AdminAnswerRepository,
    private val adminInquiryRepository: AdminInquiryRepository,
    private val adminInquiryValidationUtils: AdminInquiryValidationUtils
) : AdminInquiryCreateService, BaseService() {
    override fun createAnswer(
        inquiryId: Long,
        adminAnswerPostDto: AdminAnswerPostDto,
        memberId: Long
    ): AdminAnswerInfoDto {
        val findInquiry = adminInquiryValidationUtils.getVerifiedInquiry(inquiryId)
        val findOllie = adminInquiryValidationUtils.getVerifiedOllie(memberId)

        val createdAnswer = adminAnswerPostDto.toEntity(findInquiry, findOllie)
        val savedAnswer = adminAnswerRepository.save(createdAnswer)

        findInquiry.updateIsAnswered(true)
        adminInquiryRepository.save(findInquiry)

        return AdminAnswerInfoDto.from(savedAnswer)
    }
}