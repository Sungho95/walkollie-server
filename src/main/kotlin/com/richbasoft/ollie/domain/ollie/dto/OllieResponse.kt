package com.richbasoft.ollie.domain.ollie.dto

import com.querydsl.core.annotations.QueryProjection
import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import com.richbasoft.ollie.domain.ollie.entity.OllieItem
import com.richbasoft.ollie.domain.ollie.entity.OllieTitle
import com.richbasoft.ollie.domain.ollie.enums.Status
import com.richbasoft.ollie.domain.title.entity.Title
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema
data class OllieInfoDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val memberId: Long,

    @field:Schema(example = "홍길동")
    val name: String,

    @field:Schema(example = "10")
    val score: Int,

    @field:Schema(example = "BEST")
    val status: Status,
) {
    companion object {
        fun from(ollie: Ollie): OllieInfoDto {
            return OllieInfoDto(
                ollie.member.id!!,
                ollie.name,
                ollie.score,
                ollie.status
            )
        }
    }
}

@Schema
data class WornItemInfoDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val itemId: Long,

    @field:Schema(example = "HEAD or EYES or EAR or MOUTH or CHEEK")
    val type: Item.Type,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    val image: String
) {
    companion object {
        fun from(ollieItem: OllieItem): WornItemInfoDto {
            return WornItemInfoDto(
                itemId = ollieItem.item.id!!,
                type = ollieItem.item.type,
                image = ollieItem.item.image
            )
        }
    }
}

@Schema
data class SelectedTitleInfoDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val titleId: Long,

    @field:Schema(example = "유쾌한")
    val name: String,

    @field:Schema(example = "ADJECTIVE or NOUN")
    val type: Title.Type,
) {
    companion object {
        fun from(ollieTitle: OllieTitle): SelectedTitleInfoDto {
            return SelectedTitleInfoDto(
                titleId = ollieTitle.title.id!!,
                name = ollieTitle.title.name,
                type = ollieTitle.title.type
            )
        }
    }
}

data class OllieItemInfoDto @QueryProjection constructor(
    val itemId: Long,
    val name: String,
    val description: String,
    val type: Item.Type,
    val image: String,
    val acquired: Boolean,
    val worn: Boolean
) {
    companion object {
        fun from(ollieItem: OllieItem): OllieItemInfoDto {
            return OllieItemInfoDto(
                itemId = ollieItem.item.id!!,
                name = ollieItem.item.name,
                description = ollieItem.item.description,
                type = ollieItem.item.type,
                image = ollieItem.item.image,
                acquired = ollieItem.acquired,
                worn = ollieItem.worn
            )
        }
    }
}

data class OllieTitleInfoDto @QueryProjection constructor(
    val titleId: Long,
    val name: String,
    val description: String,
    val type: Title.Type,
    val acquired: Boolean,
    val selected: Boolean,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime
) {
    companion object {
        fun from(ollieTitle: OllieTitle): OllieTitleInfoDto {
            return OllieTitleInfoDto(
                titleId = ollieTitle.title.id!!,
                name = ollieTitle.title.name,
                description = ollieTitle.title.description,
                type = ollieTitle.title.type,
                acquired = ollieTitle.acquired,
                selected = ollieTitle.selected,
                createdAt = ollieTitle.createdAt,
                modifiedAt = ollieTitle.modifiedAt
            )
        }
    }
}