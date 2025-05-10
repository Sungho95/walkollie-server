package com.richbasoft.ollie.domain.member.service

import com.richbasoft.ollie.common.base.service.BaseService
import com.richbasoft.ollie.common.security.auth.utils.CustomAuthorityUtils
import com.richbasoft.ollie.domain.calendar.entity.Calendar
import com.richbasoft.ollie.domain.calendar.repository.CalendarRepository
import com.richbasoft.ollie.domain.member.dto.MemberPostDto
import com.richbasoft.ollie.domain.member.dto.MemberResponseDto
import com.richbasoft.ollie.domain.member.entity.Member
import com.richbasoft.ollie.domain.member.repository.MemberRepository
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import com.richbasoft.ollie.domain.ollie.repository.OllieRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class MemberCreateService(

    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val customAuthorityUtils: CustomAuthorityUtils,
    private val ollieRepository: OllieRepository,
    private val calendarRepository: CalendarRepository,
    private val memberValidationUtils: MemberValidationUtils

) : BaseService() {
    fun createMember(memberPostDto: MemberPostDto): MemberResponseDto {
        memberValidationUtils.verifyExistsDeviceId(memberPostDto.deviceId)
        val member = memberPostDto.toEntity()

        // 회원가입 초기 세팅
        val savedMember = initSettingsMember(member)
        val ollie = createInitOllie(savedMember)
        createInitCalendar(ollie)

        return MemberResponseDto.from(savedMember)
    }

    private fun initSettingsMember(member: Member): Member {
        val encryptedPassword = passwordEncoder.encode(member.password)
        member.setEncryptedPassword(encryptedPassword)

        val roles = customAuthorityUtils.createRoles(member.email)
        member.setAuthorityRoles(roles.toMutableList())

        val savedMember = memberRepository.save(member)
        return savedMember
    }

    private fun createInitOllie(savedMember: Member): Ollie {
        val ollie = Ollie(savedMember)
        return ollieRepository.save(ollie)
    }

    private fun createInitCalendar(ollie: Ollie) {
        val calendar = Calendar(0, LocalDate.now(), ollie)
        calendarRepository.save(calendar)
    }
}