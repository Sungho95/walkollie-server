package com.richbasoft.ollie.domain.inquiry.service

import com.richbasoft.ollie.common.base.service.BaseService
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.domain.inquiry.repository.InquiryRepository
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InquiryDeleteServiceImpl(
    private val inquiryRepository: InquiryRepository,
    private val memberValidationUtils: MemberValidationUtils
) : InquiryDeleteService, BaseService() {
    override fun deleteInquiry(inquiryId: Long, memberId: Long) {
        val findInquiry = inquiryRepository.findByIdOrNull(inquiryId)
            ?: throw BusinessLogicException(ExceptionCode.INQUIRY_NOT_FOUND)
        memberValidationUtils.verifyMemberIdentity(findInquiry.ollie.id!!, memberId)

        inquiryRepository.delete(findInquiry)
    }
}