package com.richbasoft.ollie.domain.ollie.repository.dsl

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.common.enums.Sort
import com.richbasoft.ollie.domain.ollie.dto.QSelectedTitleInfoDto
import com.richbasoft.ollie.domain.ollie.dto.SelectedTitleInfoDto
import com.richbasoft.ollie.domain.ollie.entity.OllieTitle
import com.richbasoft.ollie.domain.ollie.entity.QOllie.ollie
import com.richbasoft.ollie.domain.ollie.entity.QOllieTitle.ollieTitle
import com.richbasoft.ollie.domain.title.dto.TitleSearchConditionDto
import com.richbasoft.ollie.domain.title.entity.QTitle.title
import com.richbasoft.ollie.domain.title.entity.Title.Type
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.time.Instant

@Repository
class CustomOllieTitleRepositoryImpl(
    private val query: JPAQueryFactory,
    private val jdbcTemplate: JdbcTemplate
) : QuerydslRepositorySupport(OllieTitle::class.java), CustomOllieTitleRepository {

    override fun findSelectedTitleList(memberId: Long): List<SelectedTitleInfoDto> {
        return query
            .select(
                QSelectedTitleInfoDto(
                    ollieTitle.title.id,
                    ollieTitle.title.name,
                    ollieTitle.title.type
                )
            )
            .from(ollieTitle)
            .innerJoin(title)
            .on(ollieTitle.title.id.eq(title.id))
            .fetchJoin()
            .where(
                ollieTitle.ollie.id.eq(memberId),
                ollieTitle.selected.eq(true)
            )
            .orderBy(ollieTitle.title.type.asc())
            .fetch()
    }

    override fun findOllieTitle(titleId: Long, memberId: Long): OllieTitle? {
        val findOllieTitle = query
            .select(ollieTitle)
            .from(ollieTitle)
            .innerJoin(ollieTitle.title, title).fetchJoin()
            .innerJoin(ollieTitle.ollie, ollie).fetchJoin()
            .where(
                ollieTitle.title.id.eq(titleId),
                ollieTitle.ollie.id.eq(memberId),
            )
            .fetchOne()
        return findOllieTitle
    }

    override fun findSelectedTitle(titleType: Type, memberId: Long): OllieTitle? {
        val findSelectedTitle = query
            .select(ollieTitle)
            .from(ollieTitle)
            .innerJoin(ollieTitle.title, title).fetchJoin()
            .innerJoin(ollieTitle.ollie, ollie).fetchJoin()
            .where(
                ollieTitle.title.type.eq(titleType),
                ollieTitle.ollie.id.eq(memberId),
                ollieTitle.selected.eq(true)
            )
            .fetchOne()
        return findSelectedTitle
    }

    override fun findOllieTitleList(titleSearchCondition: TitleSearchConditionDto, memberId: Long): List<OllieTitle> {
        val findOllieTitleList = query
            .select(ollieTitle)
            .from(ollieTitle)
            .innerJoin(ollieTitle.ollie, ollie).fetchJoin()
            .innerJoin(ollieTitle.title, title).fetchJoin()
            .where(
                ollieTitle.ollie.id.eq(memberId),
                typeConditionEq(titleSearchCondition.type)
            )
            .orderBy(sortCondition(titleSearchCondition.sort))
            .fetch()

        return findOllieTitleList
    }

    @Transactional
    override fun bulkInsertOllieTitleList(ollieTitleList: List<OllieTitle>) {
        val now = Timestamp.from(Instant.now())
        val sql = """INSERT INTO ollie_title (acquired, selected, ollie_id, title_id, created_at, modified_at)
            VALUES (?, ?, ?, ?, ?, ?)
        """
        jdbcTemplate.batchUpdate(sql, ollieTitleList.map {
            arrayOf(it.acquired, it.selected, it.ollie.id, it.title.id, now, now)
        })
    }

    private fun typeConditionEq(type: Type): BooleanExpression? {
        return when (type) {
            Type.ALL -> null
            Type.NOUN -> ollieTitle.title.type.eq(Type.NOUN)
            Type.ADJECTIVE -> ollieTitle.title.type.eq(Type.ADJECTIVE)
        }
    }

    private fun sortCondition(sort: Sort): OrderSpecifier<*>? {
        return when (sort) {
            Sort.ASC -> ollieTitle.title.name.asc()
            Sort.DESC -> ollieTitle.title.name.desc()
        }
    }
}