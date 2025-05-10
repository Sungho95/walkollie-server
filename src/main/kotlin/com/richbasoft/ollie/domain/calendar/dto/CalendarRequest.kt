package com.richbasoft.ollie.domain.calendar.dto

import com.richbasoft.ollie.domain.calendar.entity.Calendar
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

@Schema
data class CalendarPostDto(
    @field:Schema(example = "5000")
    val steps: Int,

    @field:Schema(example = "2024-01-23")
    @field:NotBlank(message = "날짜는 필수입니다.")
    val date: LocalDate,
) {
    fun toEntity(ollie: Ollie): Calendar {
        return Calendar(
            steps = steps,
            date = date,
            ollie = ollie
        )
    }
}
