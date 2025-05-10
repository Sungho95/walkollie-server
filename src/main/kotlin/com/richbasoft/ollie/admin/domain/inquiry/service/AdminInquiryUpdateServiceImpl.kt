package com.richbasoft.ollie.admin.domain.inquiry.service

import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerInfoDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerPatchDto
import com.richbasoft.ollie.admin.domain.inquiry.repository.AdminAnswerRepository
import com.richbasoft.ollie.admin.domain.inquiry.utils.AdminInquiryValidationUtils
import com.richbasoft.ollie.common.base.service.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminInquiryUpdateServiceImpl(
    private val adminAnswerRepository: AdminAnswerRepository,
    private val adminInquiryValidationUtils: AdminInquiryValidationUtils
) : AdminInquiryUpdateService, BaseService() {
    override fun updateAnswer(inquiryId: Long, answerId: Long, answerPatchDto: AdminAnswerPatchDto): AdminAnswerInfoDto {
        val findAnswer = adminInquiryValidationUtils.getVerifiedAnswer(inquiryId)
        adminInquiryValidationUtils.verifyInquiryId(inquiryId, findAnswer)

        findAnswer.updateAnswer(answerPatchDto.content, answerPatchDto.image)
        val updatedAnswer = adminAnswerRepository.save(findAnswer)

        return AdminAnswerInfoDto.from(updatedAnswer)
    }

}