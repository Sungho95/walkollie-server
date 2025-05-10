package com.richbasoft.ollie.domain.item.dto

import com.querydsl.core.annotations.QueryProjection
import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.domain.ollie.entity.OllieItem
import io.swagger.v3.oas.annotations.media.Schema

@Schema
data class ItemInfoDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val itemId: Long,

    @field:Schema(example = "HEAD or EYES or EAR or MOUTH or CHEEK")
    val type: Item.Type,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    val image: String,

    @field:Schema(example = "true")
    val acquired: Boolean = false,

    @field:Schema(example = "false")
    val worn: Boolean = false
) {
    companion object {
        fun from(item: Item, ollieItem: OllieItem?): ItemInfoDto {
            return ItemInfoDto(
                itemId = item.id!!,
                type = item.type,
                image = item.image,
                acquired = ollieItem?.acquired ?: false,
                worn = ollieItem?.worn ?: false
            )
        }
    }
}

@Schema
data class ItemInfoDetailDto @QueryProjection constructor(
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

    @field:Schema(example = "true")
    val acquired: Boolean,

    @field:Schema(example = "false")
    val worn: Boolean
) {
    companion object {
        fun from(item: Item, ollieItem: OllieItem?): ItemInfoDetailDto {
            return ItemInfoDetailDto(
                itemId = item.id!!,
                name = item.name,
                description = item.description,
                type = item.type,
                image = item.image,
                acquired = ollieItem?.acquired ?: false,
                worn = ollieItem?.worn ?: false
            )
        }
    }
}