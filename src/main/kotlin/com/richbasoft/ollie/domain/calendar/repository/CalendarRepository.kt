package com.richbasoft.ollie.domain.calendar.repository

import com.richbasoft.ollie.domain.calendar.entity.Calendar
import com.richbasoft.ollie.domain.calendar.repository.dsl.CustomCalendarRepository
import org.springframework.data.jpa.repository.JpaRepository

interface CalendarRepository : JpaRepository<Calendar, Long>, CustomCalendarRepository {
}