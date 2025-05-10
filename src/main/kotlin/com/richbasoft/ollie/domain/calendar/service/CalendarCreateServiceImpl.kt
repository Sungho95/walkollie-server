package com.richbasoft.ollie.domain.calendar.service

import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.domain.calendar.dto.CalendarInfoDto
import com.richbasoft.ollie.domain.calendar.dto.CalendarPostDto
import com.richbasoft.ollie.domain.calendar.repository.CalendarRepository
import com.richbasoft.ollie.domain.ollie.repository.OllieRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CalendarCreateServiceImpl(
    private val calendarRepository: CalendarRepository,
    private val ollieRepository: OllieRepository
) : CalendarCreateService {
    override fun createCalendarList(
        memberId: Long,
        calendarPostDtoList: List<CalendarPostDto>
    ): List<CalendarInfoDto> {
        val findOllie = ollieRepository.findByIdOrNull(memberId)
            ?: throw BusinessLogicException(ExceptionCode.OLLIE_NOT_FOUND)

        val calendarList = calendarPostDtoList.map { it.toEntity(findOllie) }
        calendarRepository.bulkInsertCalendarList(calendarList)

        // 포인트 & 올리 점수 업데이트
        val point = calendarList.map { it.earnPoint }.sum()
        val score = calendarList.map { it.status.statusToScore() }.sum()
        findOllie.updatePointAndScore(point, score)
        ollieRepository.save(findOllie)

        return calendarList.map {
            CalendarInfoDto.from(it)
        }
    }
}