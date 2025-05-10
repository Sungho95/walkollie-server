package com.richbasoft.ollie.admin.domain.title.service

import com.richbasoft.ollie.admin.domain.title.repository.AdminTitleRepository
import com.richbasoft.ollie.admin.domain.title.utils.AdminTitleValidationUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminTitleDeleteServiceImpl(
    private val adminTitleRepository: AdminTitleRepository,
    private val adminTitleValidationUtils: AdminTitleValidationUtils
) : AdminTitleDeleteService {
    override fun deleteTitle(titleId: Long) {
        val findTitle = adminTitleValidationUtils.getVerifiedTitle(titleId)
        adminTitleRepository.delete(findTitle)
    }
}