package com.richbasoft.ollie.admin.domain.title.service

import com.richbasoft.ollie.admin.domain.title.dto.AdminTitlePatchDto
import com.richbasoft.ollie.admin.domain.title.dto.AdminTitleResponseDto

interface AdminTitleUpdateService {
    fun updateTitle(titleId: Long, adminTitlePatchDto: AdminTitlePatchDto): AdminTitleResponseDto
}