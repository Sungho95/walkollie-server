package com.richbasoft.ollie.common.security.auth

import com.richbasoft.ollie.common.base.service.BaseService
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.common.redis.RedisDao
import com.richbasoft.ollie.common.security.auth.dto.TokenRequestDto
import com.richbasoft.ollie.common.security.auth.jwt.JwtTokenProvider
import com.richbasoft.ollie.common.security.auth.memberdetails.MemberDetails
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuthService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val memberValidationUtils: MemberValidationUtils,
    private val redisDao: RedisDao
) : BaseService() {

    fun logout(memberId: Long) {
        val findMember = memberValidationUtils.getVerifiedMember(memberId)
        redisDao.expireRefreshToken(findMember.id!!)
    }

    fun accessTokenReIssue(tokenRequest: TokenRequestDto): String {
        val memberId = jwtTokenProvider.getMemberIdFromAccessToken(tokenRequest.accessToken)
        val findMember = memberValidationUtils.getVerifiedMember(memberId)
        findMember.updateLastLoginTime()

        val refreshToken = redisDao.getRefreshToken(memberId)
            ?: throw BusinessLogicException(ExceptionCode.TOKEN_IS_EXPIRED)

        if (refreshToken != tokenRequest.refreshToken) {
            throw BusinessLogicException(ExceptionCode.TOKEN_DOES_NOT_MATCH)
        }

        val memberDetails = MemberDetails.from(findMember)
        val accessToken = jwtTokenProvider.delegateAccessToken(memberDetails)

        return accessToken
    }
}