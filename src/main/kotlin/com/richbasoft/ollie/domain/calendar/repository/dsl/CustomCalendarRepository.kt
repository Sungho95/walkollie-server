package com.richbasoft.ollie.domain.calendar.repository.dsl

import com.richbasoft.ollie.domain.calendar.dto.CalendarInfoDto
import com.richbasoft.ollie.domain.calendar.entity.Calendar
import java.time.LocalDate

interface CustomCalendarRepository {
    fun findLastCalendarInfo(memberId: Long): CalendarInfoDto
    fun findMonthlyCalendar(memberId: Long, inputMoth: LocalDate, nextMonth: LocalDate): List<CalendarInfoDto>
    fun findAllByLastCalendarDate(memberId: Long, lastCalendarDate: LocalDate): List<CalendarInfoDto>
    fun findTotalStepsByMemberId(memberId: Long): Long
    fun bulkInsertCalendarList(calendarList: List<Calendar>)
}