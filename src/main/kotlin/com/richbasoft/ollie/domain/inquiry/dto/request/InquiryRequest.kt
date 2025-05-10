package com.richbasoft.ollie.domain.inquiry.dto.request

import jakarta.validation.constraints.NotBlank
import com.richbasoft.ollie.domain.inquiry.entity.Inquiry
import com.richbasoft.ollie.domain.inquiry.entity.Inquiry.Category
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length

@Schema
data class InquiryPostDto(
    @field:Schema(example = "이용 방법에 대해 문의드립니다.")
    @field:NotBlank(message = "제목을 입력해 주세요.")
    @field:NotNull(message = "제목을 입력해 주세요.")
    @field:Length(min = 1, max = 30, message = "제목은 1자 이상 30자 이하로 작성해 주세요.")
    val title: String,

    @field:Schema(example = "어떻게 해야 걸음수를 올릴 수 있죠?")
    @field:NotBlank(message = "내용을 입력해 주세요.")
    @field:NotNull(message = "내용을 입력해 주세요.")
    @field:Length(min = 1, max = 255, message = "본문은 1자 이상 255자 이하로 작성해 주세요.")
    val content: String,

    @field:Schema(example = "USE")
    @field:NotBlank(message = "카테고리를 지정해 주세요.")
    @field:NotNull(message = "카테고리를 지정해 주세요.")
    val category: String,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    val image: String = ""
) {
    fun toEntity(ollie: Ollie): Inquiry {
        return Inquiry(
            title = title,
            content = content,
            category = Category.valueOf(category),
            image = image,
            ollie = ollie
        )
    }
}

@Schema
data class InquiryPatchDto(
    @field:Schema(example = "이용 방법에 대해 문의드립니다.")
    @field:NotBlank(message = "제목을 입력해 주세요.")
    @field:NotNull(message = "제목을 입력해 주세요.")
    @field:Length(min = 1, max = 30, message = "제목은 1자 이상 30자 이하로 작성해 주세요.")
    val title: String,

    @field:Schema(example = "어떻게 해야 걸음수를 올릴 수 있죠?")
    @field:NotBlank(message = "내용을 입력해 주세요.")
    @field:NotNull(message = "내용을 입력해 주세요.")
    @field:Length(min = 1, max = 255, message = "본문은 1자 이상 255자 이하로 작성해 주세요.")
    val content: String,

    @field:Schema(example = "USE")
    @field:NotBlank(message = "카테고리를 지정해 주세요.")
    @field:NotNull(message = "카테고리를 지정해 주세요.")
    val category: String,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    val image: String?
)