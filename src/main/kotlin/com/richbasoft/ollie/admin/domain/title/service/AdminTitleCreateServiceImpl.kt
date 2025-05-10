package com.richbasoft.ollie.admin.domain.title.service

import com.richbasoft.ollie.admin.domain.title.dto.AdminTitlePostDto
import com.richbasoft.ollie.admin.domain.title.dto.AdminTitleResponseDto
import com.richbasoft.ollie.admin.domain.title.repository.AdminTitleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminTitleCreateServiceImpl(
    private val adminTitleRepository: AdminTitleRepository
) : AdminTitleCreateService {

    override fun createTitle(adminTitlePostDto: AdminTitlePostDto): AdminTitleResponseDto {
        val title = adminTitlePostDto.toEntity()
        val savedTitle = adminTitleRepository.save(title)
        return AdminTitleResponseDto.from(savedTitle)
    }
}