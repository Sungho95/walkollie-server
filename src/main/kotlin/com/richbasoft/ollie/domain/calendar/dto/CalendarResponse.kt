package com.richbasoft.ollie.domain.calendar.dto

import com.querydsl.core.annotations.QueryProjection
import com.richbasoft.ollie.domain.calendar.entity.Calendar
import com.richbasoft.ollie.domain.calendar.enums.Status
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema
data class CalendarInfoDto @QueryProjection constructor(
    @field:Schema(example = "5000")
    val steps: Int,

    @field:Schema(example = "2024-01-23")
    val date: LocalDate,

    @field:Schema(example = "1")
    val status: Status
) {
    companion object {
        fun from(calendar: Calendar): CalendarInfoDto {
            return CalendarInfoDto(
                steps = calendar.steps,
                date = calendar.date,
                status = calendar.status
            )
        }
    }
}
