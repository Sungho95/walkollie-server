package com.richbasoft.ollie.domain.ollie.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

@Schema
data class OlliePatchDto(
    @field:Schema(example = "올리올리올리숑")
    @field:NotNull(message = "name는 필수값 입니다.")
    val name: String
)

@Schema
data class OllieTitlePostDto(
    @field:Schema(example = "1")
    @field:NotNull(message = "titleId는 필수값 입니다.")
    val titleId: Long,
)

@Schema
data class WearItemPatchDto(
    @field:Schema(example = "1")
    @field:NotNull(message = "itemId는 필수값 입니다.")
    val itemId: Long,
)

@Schema
data class SelectTitlePatchDto(
    @field:Schema(example = "1")
    @field:NotNull(message = "titleId는 필수값 입니다.")
    val titleId: Long,
)

@Schema
data class OllieItemPostDto(
    @field:Schema(example = "1")
    @field:NotNull(message = "itemId는 필수값 입니다.")
    val itemId: Long,
)