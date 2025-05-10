package com.richbasoft.ollie.admin.domain.title.repository.dsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.admin.domain.title.dto.AdminTitleResponseDto
import com.richbasoft.ollie.admin.domain.title.dto.QAdminTitleResponseDto
import com.richbasoft.ollie.domain.title.entity.QTitle.title
import com.richbasoft.ollie.domain.title.entity.Title
import com.richbasoft.ollie.domain.title.entity.Title.Type.*
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class CustomAdminTitleRepositoryImpl(
    private val query: JPAQueryFactory
) : QuerydslRepositorySupport(Title::class.java), CustomAdminTitleRepository {

    override fun findTitleListByCondition(category: Title.Category, type: Title.Type): List<AdminTitleResponseDto> {
        return query
            .select(
                QAdminTitleResponseDto(
                title.id,
                title.name,
                title.description,
                title.type,
                title.category,
                title.todayStep,
                title.totalStep,
                title.createdAt,
                title.modifiedAt)
            )
            .from(title)
            .where(
                categoryConditionEq(category),
                typeConditionEq(type)
                )
            .orderBy(title.createdAt.desc())
            .fetch()
            .toList()
    }

    private fun categoryConditionEq(category: Title.Category): BooleanExpression? {

        return when (category) {
            Title.Category.ALL -> null
            else -> title.category.eq(category)
        }
    }

    private fun typeConditionEq(type: Title.Type): BooleanExpression? {

        return when (type) {
            ALL -> null
            ADJECTIVE -> title.type.eq(ADJECTIVE)
            NOUN -> title.type.eq(NOUN)
        }
    }
}