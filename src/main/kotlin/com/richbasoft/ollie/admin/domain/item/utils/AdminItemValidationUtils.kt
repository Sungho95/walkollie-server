package com.richbasoft.ollie.admin.domain.item.utils

import com.richbasoft.ollie.admin.domain.item.repository.AdminItemRepository
import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class AdminItemValidationUtils(
    private val adminItemRepository: AdminItemRepository
) {
    fun getVerifiedItem(itemId: Long): Item {
        return adminItemRepository.findByIdOrNull(itemId)
            ?: throw BusinessLogicException(ExceptionCode.ITEM_NOT_FOUND)
    }
}