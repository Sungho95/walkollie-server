package com.richbasoft.ollie.domain.title.dto

import com.querydsl.core.annotations.QueryProjection
import com.richbasoft.ollie.domain.ollie.entity.OllieTitle
import com.richbasoft.ollie.domain.title.entity.Title
import io.swagger.v3.oas.annotations.media.Schema

@Schema
data class TitleInfoDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val titleId: Long,

    @field:Schema(example = "유쾌한")
    val name: String,

    @field:Schema(example = "ADJECTIVE")
    val type: Title.Type,

    @field:Schema(example = "DAILY")
    val category: Title.Category,

    @field:Schema(example = "true")
    val acquired: Boolean,

    @field:Schema(example = "false")
    val selected: Boolean
) {
    companion object {
        fun from(title: Title, ollieTitle: OllieTitle?): TitleInfoDto {
            return TitleInfoDto(
                titleId = title.id!!,
                name = title.name,
                type = title.type,
                category = title.category,
                acquired = ollieTitle?.acquired ?: false,
                selected = ollieTitle?.selected ?: false
            )
        }
    }
}

@Schema
data class TitleInfoDetailDto @QueryProjection constructor(
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

    @field:Schema(example = "true")
    val acquired: Boolean,

    @field:Schema(example = "false")
    val selected: Boolean
) {
    companion object {
        fun from(title: Title, ollieTitle: OllieTitle?): TitleInfoDetailDto {
            return TitleInfoDetailDto(
                titleId = title.id!!,
                name = title.name,
                description = title.description,
                type = title.type,
                category = title.category,
                acquired = ollieTitle?.acquired ?: false,
                selected = ollieTitle?.selected ?: false
            )
        }
    }
}
