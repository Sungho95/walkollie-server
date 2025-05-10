package com.richbasoft.ollie.admin.domain.title.service

import com.richbasoft.ollie.admin.domain.title.dto.AdminTitleResponseDto
import com.richbasoft.ollie.admin.domain.title.repository.AdminTitleRepository
import com.richbasoft.ollie.admin.domain.title.utils.AdminTitleValidationUtils
import com.richbasoft.ollie.common.utils.validator.toEnum
import com.richbasoft.ollie.domain.title.entity.Title
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AdminTitleReadServiceImpl(
    private val adminTitleValidationUtils: AdminTitleValidationUtils,
    private val adminTitleRepository: AdminTitleRepository
) : AdminTitleReadService {
    override fun getTitle(titleId: Long): AdminTitleResponseDto {
        val findTitle = adminTitleValidationUtils.getVerifiedTitle(titleId)
        return AdminTitleResponseDto.from(findTitle)
    }

    override fun getTitleListByCondition(category: String, type: String): List<AdminTitleResponseDto> {
        val categoryCondition = category.toEnum(Title.Category::class.java)
        val typeCondition = type.toEnum(Title.Type::class.java)

        return adminTitleRepository.findTitleListByCondition(categoryCondition, typeCondition)
    }
}