package com.richbasoft.ollie.domain.calendar.service

import com.richbasoft.ollie.domain.calendar.dto.CalendarInfoDto
import com.richbasoft.ollie.domain.calendar.dto.CalendarPostDto

interface CalendarCreateService {
    fun createCalendarList(memberId: Long, calendarPostDtoList: List<CalendarPostDto>): List<CalendarInfoDto>
}