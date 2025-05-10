package com.richbasoft.ollie.admin.domain.item.repository.dsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.admin.domain.item.dto.AdminItemResponseDto
import com.richbasoft.ollie.admin.domain.item.dto.QAdminItemResponseDto
import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.domain.item.entity.Item.Type.*
import com.richbasoft.ollie.domain.item.entity.QItem.item
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class CustomAdminItemRepositoryImpl(
    private val query: JPAQueryFactory
) : QuerydslRepositorySupport(Item::class.java), CustomAdminItemRepository {

    override fun findItemListByType(typeCondition: Item.Type): List<AdminItemResponseDto> {
        return query
            .select(
                QAdminItemResponseDto(
                    item.id,
                    item.name,
                    item.description,
                    item.type,
                    item.image,
                    item.createdAt,
                    item.modifiedAt
                )
            )
            .from(item)
            .where(typeConditionEq(typeCondition))
            .orderBy(item.createdAt.desc())
            .fetch()
            .toList()
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
}