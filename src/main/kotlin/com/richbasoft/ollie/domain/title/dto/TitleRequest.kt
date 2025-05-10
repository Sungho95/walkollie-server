package com.richbasoft.ollie.domain.title.dto

import com.richbasoft.ollie.common.enums.Sort
import com.richbasoft.ollie.domain.title.entity.Title

data class TitleSearchConditionDto(
    val type: Title.Type,
    val sort: Sort,
    val acquired: Boolean
)