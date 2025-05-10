package com.richbasoft.ollie.admin.domain.inquiry.utils

import com.richbasoft.ollie.admin.domain.inquiry.repository.AdminAnswerRepository
import com.richbasoft.ollie.admin.domain.inquiry.repository.AdminInquiryRepository
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.common.utils.logger
import com.richbasoft.ollie.domain.inquiry.entity.Answer
import com.richbasoft.ollie.domain.inquiry.entity.Inquiry
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import com.richbasoft.ollie.domain.ollie.repository.OllieRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class AdminInquiryValidationUtils(
    private val adminInquiryRepository: AdminInquiryRepository,
    private val adminAnswerRepository: AdminAnswerRepository,
    private val ollieRepository: OllieRepository
) {
    val log = logger()

    fun getVerifiedInquiry(inquiryId: Long): Inquiry {
        return adminInquiryRepository.findByIdOrNull(inquiryId)
            ?: throw BusinessLogicException(ExceptionCode.INQUIRY_NOT_FOUND)
    }

    fun getVerifiedOllie(memberId: Long): Ollie {
        return ollieRepository.findByIdOrNull(memberId)
            ?: throw BusinessLogicException(ExceptionCode.OLLIE_NOT_FOUND)
    }

    fun getVerifiedAnswer(answerId: Long): Answer {
        return adminAnswerRepository.findByIdOrNull(answerId)
            ?: throw BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
    }

    fun verifyInquiryId(inquiryId: Long, answer: Answer) {
        if (inquiryId != answer.inquiry.id) {
            throw BusinessLogicException(ExceptionCode.INQUIRY_ID_NOT_MATCH)
        }
    }
}