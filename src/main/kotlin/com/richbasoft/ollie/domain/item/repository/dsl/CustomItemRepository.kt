package com.richbasoft.ollie.domain.item.repository.dsl

import com.richbasoft.ollie.domain.item.dto.ItemInfoDto
import com.richbasoft.ollie.domain.item.dto.ItemSearchConditionDto

interface CustomItemRepository {
    fun findItemInfoList(itemSearchCondition: ItemSearchConditionDto, memberId: Long): List<ItemInfoDto>
}