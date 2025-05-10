package com.richbasoft.ollie.admin.domain.item.service

import com.richbasoft.ollie.admin.domain.item.dto.AdminItemPatchDto
import com.richbasoft.ollie.admin.domain.item.dto.AdminItemResponseDto
import com.richbasoft.ollie.admin.domain.item.repository.AdminItemRepository
import com.richbasoft.ollie.admin.domain.item.utils.AdminItemValidationUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminItemUpdateServiceImpl(
    private val adminItemRepository: AdminItemRepository,
    private val adminItemValidationUtils: AdminItemValidationUtils
) : AdminItemUpdateService {
    override fun updateItem(itemId: Long, itemPatchDto: AdminItemPatchDto): AdminItemResponseDto {
        val findItem = adminItemValidationUtils.getVerifiedItem(itemId)

        findItem.updateItem(
            name = itemPatchDto.name,
            description = itemPatchDto.description,
            type = itemPatchDto.type,
            image = itemPatchDto.image
        )

        val savedItem = adminItemRepository.save(findItem)
        return AdminItemResponseDto.from(savedItem)
    }
}