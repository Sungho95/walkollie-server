package com.richbasoft.ollie.domain.ollie.repository.dsl

import com.richbasoft.ollie.domain.item.dto.ItemSearchConditionDto
import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.domain.ollie.dto.WornItemInfoDto
import com.richbasoft.ollie.domain.ollie.entity.OllieItem

interface CustomOllieItemRepository {
    fun findWornItemList(memberId: Long): List<WornItemInfoDto>

    fun findOllieItem(itemId: Long, memberId: Long): OllieItem?

    fun findWornItem(itemType: Item.Type, memberId: Long): OllieItem?

    fun findOllieItemList(itemSearchCondition: ItemSearchConditionDto, memberId: Long): List<OllieItem>
}