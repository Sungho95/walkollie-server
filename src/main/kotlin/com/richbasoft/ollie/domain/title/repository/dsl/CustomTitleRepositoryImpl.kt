package com.richbasoft.ollie.domain.title.repository.dsl

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.common.enums.Sort
import com.richbasoft.ollie.domain.calendar.dto.CalendarInfoDto
import com.richbasoft.ollie.domain.ollie.entity.QOllieTitle.ollieTitle
import com.richbasoft.ollie.domain.title.dto.*
import com.richbasoft.ollie.domain.title.entity.QTitle.title
import com.richbasoft.ollie.domain.title.entity.Title
import com.richbasoft.ollie.domain.title.entity.Title.Category.*
import com.richbasoft.ollie.domain.title.entity.Title.Type.*
import com.richbasoft.ollie.domain.title.entity.Title.Type.ALL
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
@Repository
class CustomTitleRepositoryImpl(
    private val query: JPAQueryFactory
) : QuerydslRepositorySupport(Title::class.java), CustomTitleRepository {
    override fun findTitleList(titleSearchCondition: TitleSearchConditionDto, memberId: Long): List<Title> {
        val titleList = query
            .select(title)
            .from(title)
            .where(
                typeConditionEq(titleSearchCondition.type)
            )
            .orderBy(sortCondition(titleSearchCondition.sort))
            .fetch()

        return titleList
    }

    override fun findTitleInfoList(titleSearchCondition: TitleSearchConditionDto, memberId: Long): List<TitleInfoDto> {
        val titleInfoList = query
            .select(
                QTitleInfoDto(
                    title.id,
                    title.name,
                    title.type,
                    title.category,
                    ollieTitle.acquired,
                    ollieTitle.selected
                )
            )
            .from(title)
            .leftJoin(ollieTitle).fetchJoin()
            .on(title.id.eq(ollieTitle.title.id))
            .where(
                typeConditionEq(titleSearchCondition.type),
                acquiredConditionEq(titleSearchCondition.acquired)
            )
            .orderBy(sortCondition(titleSearchCondition.sort))
            .fetch()

        return titleInfoList
    }

    override fun findCreateTargetTitleList(titleIdList: List<Long>): List<Title> {
        val titleList = query
            .select(title)
            .from(title)
            .where(title.id.`in`(titleIdList))
            .fetch()

        return titleList
    }

    override fun findObtainableTitleList(
        memberId: Long,
        calendarInfoList: List<CalendarInfoDto>,
        totalSteps: Long
    ): List<TitleInfoDto> {
        val stepsList = calendarInfoList.map {
            it.steps
        }.toList()

        val titleInfoList = query
            .select(
                QTitleInfoDto(
                    title.id,
                    title.name,
                    title.type,
                    title.category,
                    ollieTitle.acquired,
                    ollieTitle.selected
                )
            )
            .from(title)
            .leftJoin(ollieTitle).fetchJoin()
            .on(title.id.eq(ollieTitle.title.id))
            .where(
                ollieTitle.acquired.isNull,
                obtainableDailyTitleCondition(stepsList)
                    ?.or(obtainableCumulativeTitleCondition(totalSteps))
                    ?.or(obtainableHiddenTitleCondition(stepsList))
            )
            .orderBy(title.type.asc())
            .fetch()
            ?: emptyList()

        return titleInfoList
    }

    private fun typeConditionEq(type: Title.Type): BooleanExpression? {
        return when (type) {
            ALL -> null
            ADJECTIVE -> title.type.eq(ADJECTIVE)
            NOUN -> title.type.eq(NOUN)
        }
    }

    private fun acquiredConditionEq(acquired: Boolean): BooleanExpression? {
        return if (acquired) ollieTitle.acquired.eq(true) else null
    }

    private fun sortCondition(sort: Sort): OrderSpecifier<*>? {
        return when (sort) {
            Sort.ASC -> title.id.asc()
            Sort.DESC -> title.id.desc()
        }
    }

    private fun obtainableDailyTitleCondition(stepsList: List<Int>): BooleanExpression? {
        val maxTodaySteps = stepsList.max()
        return title.category.eq(DAILY)
            .and(title.todayStep.loe(maxTodaySteps))
    }

    private fun obtainableCumulativeTitleCondition(totalSteps: Long): BooleanExpression? {
        return title.category.eq(CUMULATIVE)
            .and(title.totalStep.loe(totalSteps))
    }

    private fun obtainableHiddenTitleCondition(stepsList: List<Int>): BooleanExpression? {
        return title.category.eq(HIDDEN)
            .and(title.todayStep.`in`(stepsList))
    }
}

