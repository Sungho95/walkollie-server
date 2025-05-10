package com.richbasoft.ollie.admin.domain.item.service

import com.richbasoft.ollie.admin.domain.item.dto.AdminItemPatchDto
import com.richbasoft.ollie.admin.domain.item.dto.AdminItemResponseDto

interface AdminItemUpdateService {
    fun updateItem(itemId: Long, itemPatchDto: AdminItemPatchDto): AdminItemResponseDto
}