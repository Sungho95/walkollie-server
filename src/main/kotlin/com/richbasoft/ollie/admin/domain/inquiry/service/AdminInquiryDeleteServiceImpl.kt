package com.richbasoft.ollie.admin.domain.inquiry.service

import com.richbasoft.ollie.admin.domain.inquiry.repository.AdminAnswerRepository
import com.richbasoft.ollie.admin.domain.inquiry.repository.AdminInquiryRepository
import com.richbasoft.ollie.admin.domain.inquiry.utils.AdminInquiryValidationUtils
import com.richbasoft.ollie.common.base.service.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminInquiryDeleteServiceImpl(
    private val adminAnswerRepository: AdminAnswerRepository,
    private val adminInquiryRepository: AdminInquiryRepository,
    private val adminInquiryValidationUtils: AdminInquiryValidationUtils
) : AdminInquiryDeleteService, BaseService() {
    override fun deleteAnswer(inquiryId: Long, answerId: Long) {
        val findInquiry = adminInquiryValidationUtils.getVerifiedInquiry(inquiryId)
        val findAnswer = adminInquiryValidationUtils.getVerifiedAnswer(answerId)
        adminInquiryValidationUtils.verifyInquiryId(inquiryId, findAnswer)

        findInquiry.updateIsAnswered(false)
        adminInquiryRepository.save(findInquiry)

        adminAnswerRepository.delete(findAnswer)
    }
}