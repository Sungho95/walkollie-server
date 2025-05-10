package com.richbasoft.ollie.common.security.auth.utils

import com.richbasoft.ollie.domain.member.enums.Role
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class CustomAuthorityUtils(
    @Value("\${mail.address.admin}")
    private val adminMailAddresses: List<String>,
) {

    fun createRoles(email: String): List<String> {
        return if (email in adminMailAddresses) {
            Role.values().map { it.name }.toList()
        } else {
            listOf(Role.USER.name)
        }
    }

    fun createAuthorities(roles: List<String>): List<GrantedAuthority> {
        return roles.map { role ->
            SimpleGrantedAuthority("ROLE_$role")
        }
    }
}
