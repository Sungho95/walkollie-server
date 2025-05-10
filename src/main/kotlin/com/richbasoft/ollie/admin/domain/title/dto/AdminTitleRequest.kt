package com.richbasoft.ollie.admin.domain.title.dto

import com.richbasoft.ollie.domain.title.entity.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema
data class AdminTitlePostDto(
    @field:Schema(example = "유쾌한")
    @field:NotBlank(message = "이름은 필수입니다.")
    val name: String,

    @field:Schema(example = "이 칭호은 영국에서 부터 시작되어...")
    @field:NotBlank(message = "설명은 필수입니다.")
    val description: String,

    @field:Schema(example = "ADJECTIVE or NOUN")
    @field:NotBlank(message = "명사 or 형용사를 선택해 주세요.")
    val type: String,

    @field:Schema(example = "DAILY or CUMULATIVE or HIDDEN")
    @field:NotBlank(message = "칭호 카테고리를 선택해 주세요")
    val category: String,

    @field:Schema(example = "조건1: 오늘의 걸음 수")
    val todayStep: Int,

    @field:Schema(example = "조건2: 총 걸음 수")
    val totalStep: Long,
) {
    fun toEntity(): Title {
        return Title(
            name = name,
            description = description,
            type = Title.Type.valueOf(type),
            category = Title.Category.valueOf(category),
            todayStep = todayStep,
            totalStep = totalStep,
        )
    }
}

@Schema
data class AdminTitlePatchDto(
    @field:Schema(example = "유쾌한")
    @field:NotBlank(message = "이름은 필수입니다.")
    val name: String,

    @field:Schema(example = "이 칭호은 영국에서 부터 시작되어...")
    @field:NotBlank(message = "설명은 필수입니다.")
    val description: String,

    @field:Schema(example = "ADJECTIVE or NOUN")
    @field:NotBlank(message = "명사 or 형용사를 선택해 주세요.")
    val type: String,

    @field:Schema(example = "DAILY or CUMULATIVE or HIDDEN")
    @field:NotBlank(message = "칭호 카테고리를 선택해 주세요")
    val category: String,

    @field:Schema(example = "조건1: 10000")
    val todayStep: Int,

    @field:Schema(example = "조건2: 1000000")
    val totalStep: Long
)