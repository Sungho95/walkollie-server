package com.richbasoft.ollie.domain.ollie.service

import com.richbasoft.ollie.domain.ollie.dto.*

interface OllieUpdateService {
    fun changeOllieName(olliePatchDto: OlliePatchDto, loginMemberId: Long): OllieInfoDto
    fun changeWearItem(wearItemPatchDto: WearItemPatchDto, loginMemberId: Long): List<WornItemInfoDto>
    fun takeOffWearItem(wearItemPatchDto: WearItemPatchDto, loginMemberId: Long): List<WornItemInfoDto>

    fun changeSelectTitle(
        selectTitlePatchDto: SelectTitlePatchDto,
        loginMemberId: Long
    ): List<SelectedTitleInfoDto>

    fun removeSelectTitle(
        selectTitlePatchDto: SelectTitlePatchDto,
        loginMemberId: Long
    ): List<SelectedTitleInfoDto>
}