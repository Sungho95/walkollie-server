package com.richbasoft.ollie.admin.domain.item.repository.dsl

import com.richbasoft.ollie.admin.domain.item.dto.AdminItemResponseDto
import com.richbasoft.ollie.domain.item.entity.Item

interface CustomAdminItemRepository {
    fun findItemListByType(typeCondition: Item.Type): List<AdminItemResponseDto>
}