package com.richbasoft.ollie.admin.domain.title.repository.dsl

import com.richbasoft.ollie.admin.domain.title.dto.AdminTitleResponseDto
import com.richbasoft.ollie.domain.title.entity.Title

interface CustomAdminTitleRepository {
    fun findTitleListByCondition(category: Title.Category, type: Title.Type): List<AdminTitleResponseDto>
}