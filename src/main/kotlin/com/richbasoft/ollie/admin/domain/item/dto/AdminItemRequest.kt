package com.richbasoft.ollie.admin.domain.item.dto

import com.richbasoft.ollie.domain.item.entity.Item
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema
data class ItemPostDto(
    @field:Schema(example = "머리띠")
    @field:NotBlank(message = "이름은 필수입니다.")
    val name: String,

    @field:Schema(example = "이 아이템은 영국에서 부터 시작되어...")
    @field:NotBlank(message = "설명은 필수입니다.")
    val description: String,

    @field:Schema(example = "HEAD or EYES or EAR or MOUTH or CHEEK")
    @field:NotBlank(message = "아이템 부위를 선택해 주세요.(머리, 눈, 귀, 입, 볼)")
    val type: String,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    @field:NotBlank(message = "이미지는 필수입니다.")
    val image: String,

    @field:Schema(example = "1000")
    val price: Int
) {
    fun toEntity(): Item {
        return Item(
            name = name,
            description = description,
            type = Item.Type.valueOf(type),
            image = image,
            price = price
        )
    }
}

@Schema
data class AdminItemPatchDto(
    @field:Schema(example = "유쾌한")
    @field:NotBlank(message = "이름은 필수입니다.")
    val name: String,

    @field:Schema(example = "이 아이템은 영국에서 부터 시작되어...")
    @field:NotBlank(message = "설명은 필수입니다.")
    val description: String,

    @field:Schema(example = "HEAD or EYES or EAR or MOUTH or CHEEK")
    @field:NotBlank(message = "아이템 부위를 선택해 주세요.(머리, 눈, 귀, 입, 볼)")
    val type: String,

    @field:Schema(example = "https://s3.ap-northeast-2.amazonaws.com/ollie-store/image/item/{imageName}.png")
    @field:NotBlank(message = "이미지는 필수입니다.")
    val image: String,

    @field:Schema(example = "1000")
    val price: Int
)