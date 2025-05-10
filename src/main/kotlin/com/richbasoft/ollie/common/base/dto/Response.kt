package com.richbasoft.ollie.common.base.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema
data class MultiResponse(
    @field:Schema
    val data: List<Any>
)

@Schema
data class SingleResponse(
    @field:Schema
    val data: Any
)
