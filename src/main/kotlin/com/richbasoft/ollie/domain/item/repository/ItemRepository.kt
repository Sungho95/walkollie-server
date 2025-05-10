package com.richbasoft.ollie.domain.item.repository

import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.domain.item.repository.dsl.CustomItemRepository
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long>, CustomItemRepository {
}