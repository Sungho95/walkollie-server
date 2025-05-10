package com.richbasoft.ollie.admin.domain.item.repository

import com.richbasoft.ollie.admin.domain.item.repository.dsl.CustomAdminItemRepository
import com.richbasoft.ollie.domain.item.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface AdminItemRepository : JpaRepository<Item, Long>, CustomAdminItemRepository {
}