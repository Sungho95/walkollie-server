package com.richbasoft.ollie.domain.item.repository.dsl

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.common.enums.Sort
import com.richbasoft.ollie.domain.item.dto.ItemInfoDto
import com.richbasoft.ollie.domain.item.dto.ItemSearchConditionDto
import com.richbasoft.ollie.domain.item.dto.QItemInfoDto
import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.domain.item.entity.Item.Type.*
import com.richbasoft.ollie.domain.item.entity.QItem.item
import com.richbasoft.ollie.domain.ollie.entity.QOllieItem.ollieItem
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class CustomItemRepositoryImpl(
    private val query: JPAQueryFactory
) : QuerydslRepositorySupport(Item::class.java), CustomItemRepository {

    override fun findItemInfoList(itemSearchCondition: ItemSearchConditionDto, memberId: Long): List<ItemInfoDto> {
        val itemInfoList = query
            .select(
                QItemInfoDto(
                    item.id,
                    item.type,
                    item.image,
                    ollieItem.acquired,
                    ollieItem.worn
                )
            )
            .from(item)
            .leftJoin(ollieItem).fetchJoin()
            .on(item.id.eq(ollieItem.item.id))
            .where(
                typeConditionEq(itemSearchCondition.type),
                acquiredConditionEq(itemSearchCondition.acquired)
            )
            .orderBy(sortCondition(itemSearchCondition.sort))
            .fetch()

        return itemInfoList
    }

    private fun typeConditionEq(type: Item.Type): BooleanExpression? {
        return when (type) {
            ALL -> null
            HEAD -> item.type.eq(HEAD)
            EYES -> item.type.eq(EYES)
            EAR -> item.type.eq(EAR)
            MOUTH -> item.type.eq(MOUTH)
            CHEEK -> item.type.eq(CHEEK)
        }
    }

    private fun acquiredConditionEq(acquired: Boolean): BooleanExpression? {
        return if (acquired) ollieItem.acquired.eq(true) else null
    }

    private fun sortCondition(sort: Sort): OrderSpecifier<*>? {
        return when (sort) {
            Sort.ASC -> item.id.asc()
            Sort.DESC -> item.id.desc()
        }
    }
}