package com.richbasoft.ollie.admin.domain.item.dto

import com.querydsl.core.annotations.QueryProjection
import com.richbasoft.ollie.domain.item.entity.Item
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema
data class AdminItemResponseDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val itemId: Long,

    @field:Schema(example = "머리띠")
    val name: String,

    @field:Schema(example = "이 아이템은 영국에서 부터 시작되어...")
    val description: String,

    @field:Schema(example = "HEAD or EYES or EAR or MOUTH or CHEEK")
    val type: Item.Type,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    val image: String,

    @field:Schema(example = "2023-09-17 01:12:41")
    val createdAt: LocalDateTime,

    @field:Schema(example = "2023-09-17 01:12:41")
    val modifiedAt: LocalDateTime
) {
    companion object {
        fun from(item: Item): AdminItemResponseDto {
            return AdminItemResponseDto(
                itemId = item.id!!,
                name = item.name,
                description = item.description,
                type = item.type,
                image = item.image,
                createdAt = item.createdAt,
                modifiedAt = item.modifiedAt
            )
        }
    }
}