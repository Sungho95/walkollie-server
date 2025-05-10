package com.richbasoft.ollie.domain.calendar.repository.dsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.richbasoft.ollie.domain.calendar.dto.CalendarInfoDto
import com.richbasoft.ollie.domain.calendar.dto.QCalendarInfoDto
import com.richbasoft.ollie.domain.calendar.entity.Calendar
import com.richbasoft.ollie.domain.calendar.entity.QCalendar.calendar
import com.richbasoft.ollie.domain.ollie.entity.QOllie.ollie
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate

@Repository
class CustomCalendarRepositoryImpl(
    private val query: JPAQueryFactory,
    private val jdbcTemplate: JdbcTemplate
) : QuerydslRepositorySupport(Calendar::class.java), CustomCalendarRepository {
    override fun findLastCalendarInfo(memberId: Long): CalendarInfoDto {
        val findLastCalendarInfo = query
            .select(
                QCalendarInfoDto(
                    calendar.steps,
                    calendar.date,
                    calendar.status
                )
            )
            .from(calendar)
            .innerJoin(calendar.ollie, ollie)
            .where(
                calendar.ollie.id.eq(memberId)
            )
            .orderBy(calendar.date.desc())
            .limit(1)
            .fetchOne()

        return findLastCalendarInfo!!
    }

    override fun findMonthlyCalendar(
        memberId: Long,
        inputMoth: LocalDate,
        nextMonth: LocalDate
    ): List<CalendarInfoDto> {
        val monthlyCalendarList = query
            .select(
                QCalendarInfoDto(
                    calendar.steps,
                    calendar.date,
                    calendar.status
                )
            )
            .from(calendar)
            .innerJoin(calendar.ollie, ollie)
            .where(
                calendar.ollie.id.eq(memberId),
                calendar.date.goe(inputMoth),
                calendar.date.lt(nextMonth)
            )
            .orderBy(calendar.date.asc())
            .fetch()

        return monthlyCalendarList
    }

    override fun findAllByLastCalendarDate(memberId: Long, lastCalendarDate: LocalDate): List<CalendarInfoDto> {
        val calendarInfoList = query
            .select(
                QCalendarInfoDto(
                    calendar.steps,
                    calendar.date,
                    calendar.status
                )
            )
            .from(calendar)
            .where(
                calendar.ollie.id.eq(memberId),
                lastCalendarDate.dateCondition()
            )
            .fetch()

        return calendarInfoList!!
    }

    override fun findTotalStepsByMemberId(memberId: Long): Long {
        val totalSteps = query
            .select(calendar.steps.sum())
            .from(calendar)
            .where(calendar.ollie.id.eq(memberId))
            .fetchOne()
            ?: 0

        return totalSteps.toLong()
    }

    @Transactional
    override fun bulkInsertCalendarList(calendarList: List<Calendar>) {
        val now = Timestamp.from(Instant.now())
        val sql = """INSERT INTO calendar (steps, date, earn_point, status, ollie_id, created_at, modified_at)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """
        jdbcTemplate.batchUpdate(sql, calendarList.map {
            arrayOf(it.steps, it.date, it.earnPoint, it.status, it.ollie.id, now, now)
        })
    }

    private fun LocalDate?.dateCondition(): BooleanExpression? {
        return this?.let { calendar.date.goe(it) }
    }
}