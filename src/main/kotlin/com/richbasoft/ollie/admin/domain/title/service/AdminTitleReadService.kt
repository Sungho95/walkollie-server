package com.richbasoft.ollie.admin.domain.title.service

import com.richbasoft.ollie.admin.domain.title.dto.AdminTitleResponseDto

interface AdminTitleReadService {
    fun getTitle(titleId: Long): AdminTitleResponseDto
    fun getTitleListByCondition(category: String, type: String): List<AdminTitleResponseDto>
}