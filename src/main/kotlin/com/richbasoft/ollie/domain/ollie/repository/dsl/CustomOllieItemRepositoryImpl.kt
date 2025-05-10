package com.richbasoft.ollie.domain.ollie.repository.dsl

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.common.enums.Sort
import com.richbasoft.ollie.domain.item.dto.ItemSearchConditionDto
import com.richbasoft.ollie.domain.item.entity.Item.Type
import com.richbasoft.ollie.domain.item.entity.QItem.item
import com.richbasoft.ollie.domain.ollie.dto.QWornItemInfoDto
import com.richbasoft.ollie.domain.ollie.dto.WornItemInfoDto
import com.richbasoft.ollie.domain.ollie.entity.OllieItem
import com.richbasoft.ollie.domain.ollie.entity.QOllie.ollie
import com.richbasoft.ollie.domain.ollie.entity.QOllieItem.ollieItem
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class CustomOllieItemRepositoryImpl(

    private val query: JPAQueryFactory

) : QuerydslRepositorySupport(OllieItem::class.java), CustomOllieItemRepository {

    override fun findWornItemList(memberId: Long): List<WornItemInfoDto> {
        return query
            .select(
                QWornItemInfoDto(
                    ollieItem.item.id,
                    ollieItem.item.type,
                    ollieItem.item.image
                )
            )
            .from(ollieItem)
            .innerJoin(item)
            .on(ollieItem.item.id.eq(item.id))
            .fetchJoin()
            .where(
                ollieItem.ollie.id.eq(memberId),
                ollieItem.worn.eq(true)
            )
            .orderBy(ollieItem.item.type.asc())
            .fetch()
    }

    override fun findOllieItem(itemId: Long, memberId: Long): OllieItem? {
        val findOllieItem = query
            .select(ollieItem)
            .from(ollieItem)
            .innerJoin(ollieItem.item, item).fetchJoin()
            .innerJoin(ollieItem.ollie, ollie).fetchJoin()
            .where(
                ollieItem.item.id.eq(itemId),
                ollieItem.ollie.id.eq(memberId)
            )
            .fetchOne()
        return findOllieItem
    }

    override fun findWornItem(itemType: Type, memberId: Long): OllieItem? {
        val findWornItem = query
            .select(ollieItem)
            .from(ollieItem)
            .innerJoin(ollieItem.item, item).fetchJoin()
            .innerJoin(ollieItem.ollie, ollie).fetchJoin()
            .where(
                ollieItem.item.type.eq(itemType),
                ollieItem.ollie.id.eq(memberId),
                ollieItem.worn.eq(true)
            )
            .fetchOne()
        return findWornItem
    }

    override fun findOllieItemList(itemSearchCondition: ItemSearchConditionDto, memberId: Long): List<OllieItem> {
        val findOllieItemList = query
            .select(ollieItem)
            .from(ollieItem)
            .innerJoin(ollieItem.ollie, ollie).fetchJoin()
            .innerJoin(ollieItem.item, item).fetchJoin()
            .where(
                ollieItem.ollie.id.eq(memberId),
                typeConditionEq(itemSearchCondition.type)
            )
            .orderBy(sortCondition(itemSearchCondition.sort))
            .fetch()

        return findOllieItemList
    }

    private fun typeConditionEq(type: Type): BooleanExpression? {
        return when (type) {
            Type.ALL -> null
            Type.HEAD -> ollieItem.item.type.eq(Type.HEAD)
            Type.EYES -> ollieItem.item.type.eq(Type.EYES)
            Type.EAR -> ollieItem.item.type.eq(Type.EAR)
            Type.MOUTH -> ollieItem.item.type.eq(Type.MOUTH)
            Type.CHEEK -> ollieItem.item.type.eq(Type.CHEEK)
        }
    }

    private fun sortCondition(sort: Sort): OrderSpecifier<String>? {
        return when (sort) {
            Sort.ASC -> ollieItem.item.name.asc()
            Sort.DESC -> ollieItem.item.name.desc()
        }
    }
}