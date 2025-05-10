package com.richbasoft.ollie.admin.domain.title.service

import com.richbasoft.ollie.admin.domain.title.dto.AdminTitlePatchDto
import com.richbasoft.ollie.admin.domain.title.dto.AdminTitleResponseDto
import com.richbasoft.ollie.admin.domain.title.repository.AdminTitleRepository
import com.richbasoft.ollie.admin.domain.title.utils.AdminTitleValidationUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminTitleUpdateServiceImpl(
    private val adminTitleRepository: AdminTitleRepository,
    private val adminTitleValidationUtils: AdminTitleValidationUtils
) : AdminTitleUpdateService {
    override fun updateTitle(titleId: Long, adminTitlePatchDto: AdminTitlePatchDto): AdminTitleResponseDto {
        val findTitle = adminTitleValidationUtils.getVerifiedTitle(titleId)

        findTitle.updateTitle(
            name = adminTitlePatchDto.name,
            description = adminTitlePatchDto.description,
            type = adminTitlePatchDto.type,
            category = adminTitlePatchDto.category,
            todayStep = adminTitlePatchDto.todayStep,
            totalStep = adminTitlePatchDto.totalStep,
        )

        val savedTitle = adminTitleRepository.save(findTitle)
        return AdminTitleResponseDto.from(savedTitle)
    }
}