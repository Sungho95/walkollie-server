package com.richbasoft.ollie.common.security.auth.memberdetails

import com.richbasoft.ollie.domain.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberDetails private constructor(
    val id: Long,
    val deviceId: String,
    val _password: String,
    val email: String,
    val roles: List<String>
) : UserDetails {

    companion object {
        fun from(member: Member): MemberDetails {
            return MemberDetails(
                id = member.id!!,
                deviceId = member.deviceId,
                _password = member.password,
                email = member.email,
                roles = member.roles
            )
        }

        fun from(id: Long, deviceId: String, roles: List<String>): MemberDetails {
            return MemberDetails(
                id = id,
                deviceId = deviceId,
                _password = "",
                email = "",
                roles = roles
            )
        }
    }

    override fun getAuthorities(): List<GrantedAuthority> {
        return roles.map { role ->
            SimpleGrantedAuthority("ROLE_$role")
        }
    }

    fun getAuthorityList(): List<String> {
        return roles
    }

    override fun getPassword(): String {
        return _password
    }

    override fun getUsername(): String {
        return deviceId
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}