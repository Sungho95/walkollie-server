package com.richbasoft.ollie.admin.domain.item.service

import com.richbasoft.ollie.admin.domain.item.dto.AdminItemResponseDto

interface AdminItemReadService {
    fun getItem(itemId: Long): AdminItemResponseDto
    fun getItemListByType(type: String): List<AdminItemResponseDto>
}