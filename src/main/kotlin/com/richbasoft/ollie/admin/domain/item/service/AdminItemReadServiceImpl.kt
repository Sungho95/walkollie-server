package com.richbasoft.ollie.admin.domain.item.service

import com.richbasoft.ollie.admin.domain.item.dto.AdminItemResponseDto
import com.richbasoft.ollie.admin.domain.item.repository.AdminItemRepository
import com.richbasoft.ollie.admin.domain.item.utils.AdminItemValidationUtils
import com.richbasoft.ollie.common.utils.validator.toEnum
import com.richbasoft.ollie.domain.item.entity.Item
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AdminItemReadServiceImpl(
    private val adminItemRepository: AdminItemRepository,
    private val adminItemValidationUtils: AdminItemValidationUtils
) : AdminItemReadService {
    override fun getItem(itemId: Long): AdminItemResponseDto {
        val findItem = adminItemValidationUtils.getVerifiedItem(itemId)
        return AdminItemResponseDto.from(findItem)
    }

    override fun getItemListByType(type: String): List<AdminItemResponseDto> {
        val typeCondition = type.toEnum(Item.Type::class.java)
        return adminItemRepository.findItemListByType(typeCondition)
    }
}