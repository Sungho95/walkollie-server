package com.richbasoft.ollie.admin.domain.title.dto

import com.querydsl.core.annotations.QueryProjection
import com.richbasoft.ollie.domain.title.entity.*
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema
data class AdminTitleResponseDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val titleId: Long,

    @field:Schema(example = "유쾌한")
    val name: String,

    @field:Schema(example = "이 타이틀은 영국에서 부터 시작되어...")
    val description: String,

    @field:Schema(example = "ADJECTIVE")
    val type: Title.Type,

    @field:Schema(example = "DAILY")
    val category: Title.Category,

    @field:Schema(example = "10000")
    val todayStep: Int,

    @field:Schema(example = "null")
    val totalStep: Long,

    @field:Schema(example = "2023-09-17 01:12:41")
    val createdAt: LocalDateTime,

    @field:Schema(example = "2023-09-17 01:12:41")
    val modifiedAt: LocalDateTime
) {
    companion object {
        fun from(title: Title): AdminTitleResponseDto {
            return AdminTitleResponseDto(
                titleId = title.id!!,
                name = title.name,
                description = title.description,
                type = title.type,
                category = title.category,
                todayStep = title.todayStep,
                totalStep = title.totalStep,
                createdAt = title.createdAt,
                modifiedAt = title.modifiedAt
            )
        }
    }
}