package com.richbasoft.ollie.domain.ollie.service

import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import com.richbasoft.ollie.domain.ollie.dto.OllieInfoDto
import com.richbasoft.ollie.domain.ollie.dto.SelectedTitleInfoDto
import com.richbasoft.ollie.domain.ollie.dto.WornItemInfoDto
import com.richbasoft.ollie.domain.ollie.repository.OllieItemRepository
import com.richbasoft.ollie.domain.ollie.repository.OllieRepository
import com.richbasoft.ollie.domain.ollie.repository.OllieTitleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OllieReadServiceImpl(
    private val ollieRepository: OllieRepository,
    private val ollieItemRepository: OllieItemRepository,
    private val ollieTitleRepository: OllieTitleRepository,
    private val memberValidationUtils: MemberValidationUtils
) : OllieReadService {

    override fun getOllieInfo(memberId: Long, loginMemberId: Long): OllieInfoDto {
        memberValidationUtils.verifyMemberIdentity(memberId, loginMemberId)
        return ollieRepository.findOllieInfo(memberId)
    }

    override fun getWornItemList(memberId: Long, loginMemberId: Long): List<WornItemInfoDto> {
        memberValidationUtils.verifyMemberIdentity(memberId, loginMemberId)
        return ollieItemRepository.findWornItemList(memberId)
    }

    override fun getSelectedTitleList(memberId: Long, loginMemberId: Long): List<SelectedTitleInfoDto> {
        memberValidationUtils.verifyMemberIdentity(memberId, loginMemberId)
        return ollieTitleRepository.findSelectedTitleList(memberId)
    }
}