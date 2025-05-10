package com.richbasoft.ollie.admin.domain.inquiry.dto

import com.richbasoft.ollie.domain.inquiry.entity.Answer
import com.richbasoft.ollie.domain.inquiry.entity.Inquiry
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema
data class AdminAnswerPostDto(
    @field:Schema(example = "이렇게 저렇게 해야 걸음수를 올릴 수 있습니다.")
    @field:NotBlank(message = "내용을 입력해 주세요.")
    @field:NotNull(message = "내용을 입력해 주세요.")
    val content: String,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    val image: String = "",
) {
    fun toEntity(inquiry: Inquiry, ollie: Ollie): Answer {
        return Answer(
            content = content,
            image = image,
            inquiry = inquiry,
            ollie = ollie
        )
    }
}

@Schema
data class AdminAnswerPatchDto(
    @field:Schema(example = "이렇게 저렇게 해야 걸음수를 올릴 수 있습니다.")
    @field:NotBlank(message = "내용을 입력해 주세요.")
    @field:NotNull(message = "내용을 입력해 주세요.")
    val content: String,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    val image: String = "",
)