package com.richbasoft.ollie.domain.item.dto

import com.richbasoft.ollie.common.enums.Sort
import com.richbasoft.ollie.domain.item.entity.Item

data class ItemSearchConditionDto(
    val type: Item.Type,
    val sort: Sort,
    val acquired: Boolean
)