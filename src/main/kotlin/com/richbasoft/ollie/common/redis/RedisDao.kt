package com.richbasoft.ollie.common.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisDao(
    private val redisTemplate: RedisTemplate<String, Any>,

    @Value("\${jwt.refresh-token-expiration-minutes}")
    val refreshTokenExpirationMinutes: Int
) {
    private fun generateKey(memberId: Long) = RedisKey.REFRESH_TOKEN + memberId

    fun getRefreshToken(memberId: Long): String? {
        return redisTemplate.opsForValue()[generateKey(memberId)] as? String
    }

    fun setRefreshToken(memberId: Long, jws: String) {
        val key = generateKey(memberId)
        val duration = Duration.ofMinutes(refreshTokenExpirationMinutes.toLong())
        redisTemplate.opsForValue()[key] = jws
        redisTemplate.expire(key, duration)
    }

    fun expireRefreshToken(memberId: Long) {
        redisTemplate.expire(generateKey(memberId), Duration.ZERO)
    }
}