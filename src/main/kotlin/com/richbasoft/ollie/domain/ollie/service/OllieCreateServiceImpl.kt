package com.richbasoft.ollie.domain.ollie.service

import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.domain.item.repository.ItemRepository
import com.richbasoft.ollie.domain.ollie.dto.OllieItemInfoDto
import com.richbasoft.ollie.domain.ollie.dto.OllieItemPostDto
import com.richbasoft.ollie.domain.ollie.dto.OllieTitleInfoDto
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import com.richbasoft.ollie.domain.ollie.entity.OllieItem
import com.richbasoft.ollie.domain.ollie.entity.OllieTitle
import com.richbasoft.ollie.domain.ollie.repository.OllieItemRepository
import com.richbasoft.ollie.domain.ollie.repository.OllieRepository
import com.richbasoft.ollie.domain.title.repository.TitleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OllieCreateServiceImpl(
    private val ollieRepository: OllieRepository,
    private val titleRepository: TitleRepository,
    private val itemRepository: ItemRepository,
    private val ollieItemRepository: OllieItemRepository
) : OllieCreateService {
    override fun createOllieTitleList(
        titleIdList: List<Long>,
        memberId: Long
    ): List<OllieTitleInfoDto> {
        val findOllie = getVerifiedOllie(memberId)
        val findTitleList = titleRepository.findCreateTargetTitleList(titleIdList)

        val ollieTitleList = findTitleList.mapTo(mutableListOf()) {
            OllieTitle(findOllie, it).also { ollieTitle ->
                findOllie.addTitle(ollieTitle)
            }
        }.toList()

        ollieRepository.save(findOllie)

        return ollieTitleList.map {
            OllieTitleInfoDto.from(it)
        }
    }

    override fun createOllieItem(ollieItemPostDto: OllieItemPostDto, memberId: Long): OllieItemInfoDto {
        val ollie = getVerifiedOllie(memberId)
        checkIfOllieItemExists(ollieItemPostDto, memberId)
        val item = getVerifiedItem(ollieItemPostDto)
        checkPoints(ollie, item)

        val ollieItem = OllieItem(ollie, item)
        ollie.purchaseItem(ollieItem, item.price)
        ollieRepository.save(ollie)

        return OllieItemInfoDto.from(ollieItem)
    }

    private fun checkPoints(
        ollie: Ollie,
        item: Item
    ) {
        if (ollie.point < item.price) {
            throw BusinessLogicException(ExceptionCode.INSUFFICIENT_POINTS)
        }
    }

    private fun checkIfOllieItemExists(ollieItemPostDto: OllieItemPostDto, memberId: Long) {
        val findOllieItem = ollieItemRepository.findOllieItem(ollieItemPostDto.itemId, memberId)
        if (findOllieItem != null) {
            throw BusinessLogicException(ExceptionCode.OLLIE_ITEM_IS_EXISTS)
        }
    }

    private fun getVerifiedItem(ollieItemPostDto: OllieItemPostDto): Item {
        return itemRepository.findByIdOrNull(ollieItemPostDto.itemId)
            ?: throw BusinessLogicException(ExceptionCode.ITEM_NOT_FOUND)
    }

    private fun getVerifiedOllie(memberId: Long): Ollie {
        return ollieRepository.findByIdOrNull(memberId)
            ?: throw BusinessLogicException(ExceptionCode.OLLIE_NOT_FOUND)
    }
}