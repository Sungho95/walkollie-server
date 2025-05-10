package com.richbasoft.ollie.admin.domain.title.utils

import com.richbasoft.ollie.admin.domain.title.repository.AdminTitleRepository
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.domain.title.entity.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class AdminTitleValidationUtils(
    private val adminTitleRepository: AdminTitleRepository
) {

    fun getVerifiedTitle(titleId: Long): Title {
        return adminTitleRepository.findByIdOrNull(titleId)
            ?: throw BusinessLogicException(ExceptionCode.TITLE_NOT_FOUND)
    }
}