package com.richbasoft.ollie.domain.inquiry.service

import com.richbasoft.ollie.common.base.service.BaseService
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.domain.inquiry.dto.request.InquiryPatchDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDetailDto
import com.richbasoft.ollie.domain.inquiry.repository.InquiryRepository
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InquiryUpdateServiceImpl(
    private val inquiryRepository: InquiryRepository,
    private val memberValidationUtils: MemberValidationUtils
) : InquiryUpdateService, BaseService() {
    override fun updateInquiry(inquiryId: Long, inquiryPatchDto: InquiryPatchDto, memberId: Long): InquiryInfoDetailDto {
        val findInquiry = inquiryRepository.findByIdOrNull(inquiryId)
            ?: throw BusinessLogicException(ExceptionCode.INQUIRY_NOT_FOUND)
        memberValidationUtils.verifyMemberIdentity(findInquiry.ollie.id!!, memberId)

        findInquiry.updateInquiry(
            title = inquiryPatchDto.title,
            content = inquiryPatchDto.content,
            category = inquiryPatchDto.category,
            image = inquiryPatchDto.image ?: ""
        )

        val updatedInquiry = inquiryRepository.save(findInquiry)

        return InquiryInfoDetailDto.from(updatedInquiry)
    }
}