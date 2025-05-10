package com.richbasoft.ollie.domain.ollie.service

import com.richbasoft.ollie.domain.ollie.dto.*
import com.richbasoft.ollie.domain.ollie.repository.OllieItemRepository
import com.richbasoft.ollie.domain.ollie.repository.OllieRepository
import com.richbasoft.ollie.domain.ollie.repository.OllieTitleRepository
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OllieUpdateServiceImpl(
    private val ollieRepository: OllieRepository,
    private val ollieItemRepository: OllieItemRepository,
    private val ollieTitleRepository: OllieTitleRepository,
) : OllieUpdateService {
    override fun changeOllieName(olliePatchDto: OlliePatchDto, loginMemberId: Long): OllieInfoDto {
        val findOllie = ollieRepository.findByIdOrNull(loginMemberId)
            ?: throw BusinessLogicException(ExceptionCode.OLLIE_NOT_FOUND)

        findOllie.changeName(olliePatchDto.name)
        val savedOllie = ollieRepository.save(findOllie)

        return OllieInfoDto.from(savedOllie)
    }

    override fun changeWearItem(wearItemPatchDto: WearItemPatchDto, loginMemberId: Long): List<WornItemInfoDto> {
        val findOllieItem = ollieItemRepository.findOllieItem(wearItemPatchDto.itemId, loginMemberId)
            ?: throw BusinessLogicException(ExceptionCode.OLLIE_ITEM_NOT_FOUND)

        val findWornItem = ollieItemRepository.findWornItem(findOllieItem.item.type, loginMemberId)
        findWornItem?.let {
            it.changeWorn(false)
            ollieItemRepository.save(it)
        }

        findOllieItem.changeWorn(true)
        ollieItemRepository.save(findOllieItem)

        return ollieItemRepository.findWornItemList(loginMemberId)
    }

    override fun takeOffWearItem(wearItemPatchDto: WearItemPatchDto, loginMemberId: Long): List<WornItemInfoDto> {
        val findOllieItem = ollieItemRepository.findOllieItem(wearItemPatchDto.itemId, loginMemberId)
            ?: throw BusinessLogicException(ExceptionCode.OLLIE_ITEM_NOT_FOUND)

        findOllieItem.changeWorn(false)
        ollieItemRepository.save(findOllieItem)

        return ollieItemRepository.findWornItemList(loginMemberId)
    }

    override fun changeSelectTitle(
        selectTitlePatchDto: SelectTitlePatchDto,
        loginMemberId: Long
    ): List<SelectedTitleInfoDto> {
        val findOllieTitle = ollieTitleRepository.findOllieTitle(selectTitlePatchDto.titleId, loginMemberId)
            ?: throw BusinessLogicException(ExceptionCode.OLLIE_TITLE_NOT_FOUND)

        val findSelectedTitle = ollieTitleRepository.findSelectedTitle(findOllieTitle.title.type, loginMemberId)
        findSelectedTitle?.let {
            it.changeSelected(false)
            ollieTitleRepository.save(it)
        }

        findOllieTitle.changeSelected(true)
        ollieTitleRepository.save(findOllieTitle)

        return ollieTitleRepository.findSelectedTitleList(loginMemberId)
    }

    override fun removeSelectTitle(
        selectTitlePatchDto: SelectTitlePatchDto,
        loginMemberId: Long
    ): List<SelectedTitleInfoDto> {
        val findOllieTitle = ollieTitleRepository.findOllieTitle(selectTitlePatchDto.titleId, loginMemberId)
            ?: throw BusinessLogicException(ExceptionCode.OLLIE_TITLE_NOT_FOUND)

        findOllieTitle.changeSelected(false)
        ollieTitleRepository.save(findOllieTitle)

        return ollieTitleRepository.findSelectedTitleList(loginMemberId)
    }
}