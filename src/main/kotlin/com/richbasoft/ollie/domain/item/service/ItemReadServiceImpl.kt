package com.richbasoft.ollie.domain.item.service

import com.richbasoft.ollie.domain.item.dto.ItemInfoDto
import com.richbasoft.ollie.domain.item.dto.ItemInfoDetailDto
import com.richbasoft.ollie.domain.item.dto.ItemSearchConditionDto
import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.domain.item.repository.ItemRepository
import com.richbasoft.ollie.domain.ollie.repository.OllieItemRepository
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemReadServiceImpl(
    private val itemRepository: ItemRepository,
    private val ollieItemRepository: OllieItemRepository
) : ItemReadService {

    override fun getItemInfoList(searchCondition: ItemSearchConditionDto, memberId: Long): List<ItemInfoDto> {
        val findItemInfoList = itemRepository.findItemInfoList(searchCondition, memberId)

        return findItemInfoList
    }

    override fun getItemInfoDetail(itemId: Long, memberId: Long): ItemInfoDetailDto {
        val findItem = getVerifiedItem(itemId)
        val findOllieItem = ollieItemRepository.findOllieItem(itemId, memberId)

        return ItemInfoDetailDto.from(findItem, findOllieItem)
    }

    private fun getVerifiedItem(itemId: Long): Item {
        return itemRepository.findByIdOrNull(itemId)
            ?: throw BusinessLogicException(ExceptionCode.ITEM_NOT_FOUND)
    }
}