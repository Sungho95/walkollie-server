package com.richbasoft.ollie.common.security.auth.jwt

import com.richbasoft.ollie.common.redis.RedisDao
import com.richbasoft.ollie.common.security.auth.memberdetails.MemberDetails
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.key.secret}")
    val secretKey: String,

    @Value("\${jwt.access-token-expiration-minutes}")
    val accessTokenExpirationMinutes: Int,

    @Value("\${jwt.refresh-token-expiration-minutes}")
    val refreshTokenExpirationMinutes: Int,

    private val redisDao: RedisDao
) {
    fun encodeBase64SecretKey(): String {
        return Encoders.BASE64.encode(secretKey.toByteArray(StandardCharsets.UTF_8))
    }

    fun generateAccessToken(
        claims: Map<String, Any>,
        subject: String,
        expiration: Date
    ): String {
        val key = getKeyFromBase64EncodedKey()

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Calendar.getInstance().time)
            .setExpiration(expiration)
            .signWith(key)
            .compact()
    }

    fun generateRefreshToken(subject: String, expiration: Date): String {
        val key = getKeyFromBase64EncodedKey()

        return Jwts.builder()
            .setSubject(subject)
            .setIssuedAt(Calendar.getInstance().time)
            .setExpiration(expiration)
            .signWith(key)
            .compact()
    }

    fun getClaims(jws: String): Jws<Claims> {
        val key = getKeyFromBase64EncodedKey()

        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jws)
    }

    fun verifySignature(jws: String) {
        val key = getKeyFromBase64EncodedKey()

        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jws)
    }

    fun getTokenExpiration(expirationMinutes: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, expirationMinutes)

        return calendar.time
    }

    fun getMemberIdFromAccessToken(accessToken: String): Long {
        val claims = getClaims(accessToken).body
        return claims["id"].toString().toLong()
    }

    private fun getKeyFromBase64EncodedKey(): Key {
        val keyBytes = Decoders.BASE64.decode(encodeBase64SecretKey())
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun delegateAccessToken(memberDetails: MemberDetails): String {
        val claims = mutableMapOf<String, Any>()
        claims.put("id", memberDetails.id.toString())
        claims.put("roles", memberDetails.roles)

        val subject = memberDetails.deviceId
        val expiration = getTokenExpiration(accessTokenExpirationMinutes)
        val accessToken = generateAccessToken(claims, subject, expiration)

        return accessToken
    }

    fun delegateRefreshToken(memberDetails: MemberDetails): String {
        val subject = memberDetails.deviceId
        val expiration = getTokenExpiration(refreshTokenExpirationMinutes)
        val refreshToken = generateRefreshToken(subject, expiration)
        redisDao.setRefreshToken(memberDetails.id, refreshToken)

        return refreshToken
    }
}