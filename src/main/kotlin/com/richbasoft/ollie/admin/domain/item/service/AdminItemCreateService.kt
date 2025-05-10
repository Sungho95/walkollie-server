package com.richbasoft.ollie.admin.domain.item.service

import com.richbasoft.ollie.admin.domain.item.dto.AdminItemResponseDto
import com.richbasoft.ollie.admin.domain.item.dto.ItemPostDto

interface AdminItemCreateService {
    fun createItem(itemPostDto: ItemPostDto): AdminItemResponseDto
}