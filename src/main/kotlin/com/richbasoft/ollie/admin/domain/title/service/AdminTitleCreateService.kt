package com.richbasoft.ollie.admin.domain.title.service

import com.richbasoft.ollie.admin.domain.title.dto.AdminTitlePostDto
import com.richbasoft.ollie.admin.domain.title.dto.AdminTitleResponseDto

interface AdminTitleCreateService {
    fun createTitle(adminTitlePostDto: AdminTitlePostDto): AdminTitleResponseDto
}