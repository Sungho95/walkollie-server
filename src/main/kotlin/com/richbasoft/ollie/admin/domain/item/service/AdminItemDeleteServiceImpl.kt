package com.richbasoft.ollie.admin.domain.item.service

import com.richbasoft.ollie.admin.domain.item.repository.AdminItemRepository
import com.richbasoft.ollie.admin.domain.item.utils.AdminItemValidationUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminItemDeleteServiceImpl(
    private val adminItemRepository: AdminItemRepository,
    private val adminItemValidationUtils: AdminItemValidationUtils
) : AdminItemDeleteService {
    override fun deleteItem(itemId: Long) {
        val findItem = adminItemValidationUtils.getVerifiedItem(itemId)
        adminItemRepository.delete(findItem)
    }
}