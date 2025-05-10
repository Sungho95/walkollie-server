package com.richbasoft.ollie.domain.inquiry.service

import com.richbasoft.ollie.common.base.service.BaseService
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.domain.inquiry.dto.response.AnswerInfoDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDetailDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDto
import com.richbasoft.ollie.domain.inquiry.entity.Inquiry
import com.richbasoft.ollie.domain.inquiry.repository.InquiryRepository
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class InquiryReadServiceImpl(
    private val inquiryRepository: InquiryRepository,
    private val memberValidationUtils: MemberValidationUtils
) : InquiryReadService, BaseService() {
    override fun getInquiryList(memberId: Long): List<InquiryInfoDto> {
        val findInquiryList = inquiryRepository.findInquiryList(memberId)

        return findInquiryList
    }

    override fun getInquiry(inquiryId: Long, memberId: Long): InquiryInfoDetailDto {
        val findInquiry = getVerifiedInquiry(inquiryId)
        memberValidationUtils.verifyMemberIdentity(findInquiry.ollie.id!!, memberId)

        return InquiryInfoDetailDto.from(findInquiry)
    }

    override fun getInquiryAnswer(inquiryId: Long, memberId: Long): AnswerInfoDto {
        val findInquiry = getVerifiedInquiry(inquiryId)
        memberValidationUtils.verifyMemberIdentity(findInquiry.ollie.id!!, memberId)

        verifyInquiryAnswered(findInquiry)

        val findAnswerInfoDto = inquiryRepository.findInquiryAnswer(inquiryId)
        return findAnswerInfoDto
    }

    private fun getVerifiedInquiry(inquiryId: Long): Inquiry {
        val findInquiry = inquiryRepository.findByIdOrNull(inquiryId)
            ?: throw BusinessLogicException(ExceptionCode.INQUIRY_NOT_FOUND)
        return findInquiry
    }

    private fun verifyInquiryAnswered(findInquiry: Inquiry) {
        if (!findInquiry.isAnswered) {
            throw BusinessLogicException(ExceptionCode.INQUIRY_IS_NOT_ANSWERED)
        }
    }
}