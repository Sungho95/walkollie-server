package com.richbasoft.ollie.domain.inquiry.service

import com.richbasoft.ollie.common.base.service.BaseService
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.domain.inquiry.dto.request.InquiryPostDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDetailDto
import com.richbasoft.ollie.domain.inquiry.repository.InquiryRepository
import com.richbasoft.ollie.domain.ollie.repository.OllieRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InquiryCreateServiceImpl(
    private val inquiryRepository: InquiryRepository,
    private val ollieRepository: OllieRepository
) : InquiryCreateService, BaseService() {
    override fun createInquiry(inquiryPostDto: InquiryPostDto, memberId: Long): InquiryInfoDetailDto {
        val findOllie = ollieRepository.findByIdOrNull(memberId)
            ?: throw BusinessLogicException(ExceptionCode.OLLIE_NOT_FOUND)
        val inquiry = inquiryPostDto.toEntity(findOllie)

        val savedInquiry = inquiryRepository.save(inquiry)

        return InquiryInfoDetailDto.from(savedInquiry)
    }
}