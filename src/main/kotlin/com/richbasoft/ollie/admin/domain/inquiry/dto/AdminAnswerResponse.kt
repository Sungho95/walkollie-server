package com.richbasoft.ollie.admin.domain.inquiry.dto

import com.querydsl.core.annotations.QueryProjection
import com.richbasoft.ollie.domain.inquiry.entity.Answer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema
data class AdminAnswerInfoDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val answerId: Long,

    @field:Schema(example = "1")
    val inquiryId: Long,

    @field:Schema(example = "운영자올리올리숑")
    val ollieName: String,

    @field:Schema(example = "이렇게 저렇게 해야 걸음수를 올릴 수 있습니다.")
    val content: String,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    val image: String = "",

    @field:Schema(example = "2023-09-17 01:12:41")
    val createdAt: LocalDateTime,

    @field:Schema(example = "2023-09-17 01:12:41")
    val modified1At: LocalDateTime
) {
    companion object {
        fun from(answer: Answer): AdminAnswerInfoDto {
            return AdminAnswerInfoDto(
                answer.id!!,
                answer.inquiry.id!!,
                answer.admin.name,
                answer.content,
                answer.image,
                answer.createdAt,
                answer.modifiedAt
            )
        }
    }
}
