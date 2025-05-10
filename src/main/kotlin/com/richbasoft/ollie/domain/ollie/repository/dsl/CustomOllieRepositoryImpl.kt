package com.richbasoft.ollie.domain.ollie.repository.dsl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.domain.ollie.dto.OllieInfoDto
import com.richbasoft.ollie.domain.ollie.dto.QOllieInfoDto
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import com.richbasoft.ollie.domain.ollie.entity.QOllie.ollie
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class CustomOllieRepositoryImpl(
    private val query: JPAQueryFactory
) : QuerydslRepositorySupport(Ollie::class.java), CustomOllieRepository {
    override fun findOllieInfo(memberId: Long): OllieInfoDto {
        return query
            .select(
                QOllieInfoDto(
                    ollie.id,
                    ollie.name,
                    ollie.score,
                    ollie.status
                )
            )
            .from(ollie)
            .where(ollie.id.eq(memberId))
            .fetchOne()!!
    }
}