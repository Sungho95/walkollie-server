package com.richbasoft.ollie.admin.domain.item.service

import com.richbasoft.ollie.admin.domain.item.dto.AdminItemResponseDto
import com.richbasoft.ollie.admin.domain.item.dto.ItemPostDto
import com.richbasoft.ollie.admin.domain.item.repository.AdminItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminItemCreateServiceImpl(
    private val adminItemRepository: AdminItemRepository
) : AdminItemCreateService {
    override fun createItem(itemPostDto: ItemPostDto): AdminItemResponseDto {
        val item = itemPostDto.toEntity()
        val savedItem = adminItemRepository.save(item)
        return AdminItemResponseDto.from(savedItem)
    }
}