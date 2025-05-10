package com.richbasoft.ollie.domain.calendar.service

import com.richbasoft.ollie.common.base.service.BaseService
import com.richbasoft.ollie.domain.calendar.dto.CalendarInfoDto
import com.richbasoft.ollie.domain.calendar.repository.CalendarRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class CalendarReadServiceImpl(
    private val calendarRepository: CalendarRepository
) : CalendarReadService, BaseService() {
    override fun getLastCalendarInfo(memberId: Long): CalendarInfoDto {
        val findLastCalendarInfo = calendarRepository.findLastCalendarInfo(memberId)
        return findLastCalendarInfo
    }

    override fun getMonthlyCalendar(memberId: Long, year: Int, month: Int): List<CalendarInfoDto> {
        val inputMonth = LocalDate.of(year, month, 1)
        val nextMonth = inputMonth.plusMonths(1)

        val monthlyCalendarList = calendarRepository.findMonthlyCalendar(memberId, inputMonth, nextMonth)
        return monthlyCalendarList
    }

    override fun getTotalSteps(memberId: Long): Long {
        return calendarRepository.findTotalStepsByMemberId(memberId)
    }
}