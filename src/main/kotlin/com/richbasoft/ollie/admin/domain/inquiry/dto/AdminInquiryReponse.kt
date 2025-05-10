package com.richbasoft.ollie.admin.domain.inquiry.dto

import com.querydsl.core.annotations.QueryProjection
import com.richbasoft.ollie.domain.inquiry.entity.Inquiry
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema
data class AdminInquiryInfoDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val inquiryId: Long,

    @field:Schema(example = "1")
    val memberId: Long,

    @field:Schema(example = "올리올리올리숑")
    val ollieName: String,

    @field:Schema(example = "이용 방법에 대해 문의드립니다.")
    val title: String,

    @field:Schema(example = "false")
    val isAnswered: Boolean,

    @field:Schema(example = "2023-09-17 01:12:41")
    val createdAt: LocalDateTime,

    @field:Schema(example = "2023-09-17 01:12:41")
    val modified1At: LocalDateTime
) {
    companion object {
        fun from(inquiry: Inquiry): AdminInquiryInfoDto {
            return AdminInquiryInfoDto(
                inquiryId = inquiry.id!!,
                memberId = inquiry.ollie.id!!,
                ollieName = inquiry.ollie.name,
                title = inquiry.title,
                isAnswered = inquiry.isAnswered,
                createdAt = inquiry.createdAt,
                modified1At = inquiry.modifiedAt
            )
        }
    }
}

@Schema
data class AdminInquiryInfoDetailDto @QueryProjection constructor(
    @field:Schema(example = "1")
    val inquiryId: Long,

    @field:Schema(example = "1")
    val memberId: Long,

    @field:Schema(example = "올리올리올리숑")
    val ollieName: String,

    @field:Schema(example = "이용 방법에 대해 문의드립니다.")
    val title: String,

    @field:Schema(example = "어떻게 해야 걸음수를 올릴 수 있죠?.")
    val content: String,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    val image: String,

    @field:Schema(example = "false")
    val isAnswered: Boolean,

    @field:Schema(example = "2023-09-17 01:12:41")
    val createdAt: LocalDateTime,

    @field:Schema(example = "2023-09-17 01:12:41")
    val modified1At: LocalDateTime
) {
    companion object {
        fun from(inquiry: Inquiry): AdminInquiryInfoDetailDto {
            return AdminInquiryInfoDetailDto(
                inquiryId = inquiry.id!!,
                memberId = inquiry.ollie.id!!,
                ollieName = inquiry.ollie.name,
                title = inquiry.title,
                content = inquiry.content,
                image = inquiry.image,
                isAnswered = inquiry.isAnswered,
                createdAt = inquiry.createdAt,
                modified1At = inquiry.modifiedAt
            )
        }
    }
}