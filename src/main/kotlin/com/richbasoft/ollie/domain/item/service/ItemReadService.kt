package com.richbasoft.ollie.domain.item.service

import com.richbasoft.ollie.domain.item.dto.ItemInfoDto
import com.richbasoft.ollie.domain.item.dto.ItemInfoDetailDto
import com.richbasoft.ollie.domain.item.dto.ItemSearchConditionDto

interface ItemReadService {
    fun getItemInfoList(searchCondition: ItemSearchConditionDto, memberId: Long): List<ItemInfoDto>

    fun getItemInfoDetail(itemId: Long, memberId: Long): ItemInfoDetailDto
}