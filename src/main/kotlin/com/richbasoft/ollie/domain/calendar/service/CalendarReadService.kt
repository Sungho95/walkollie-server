package com.richbasoft.ollie.domain.calendar.service

import com.richbasoft.ollie.domain.calendar.dto.CalendarInfoDto

interface CalendarReadService {
    fun getLastCalendarInfo(memberId: Long): CalendarInfoDto
    fun getMonthlyCalendar(memberId: Long, year: Int, month: Int): List<CalendarInfoDto>
    fun getTotalSteps(memberId: Long): Long
}