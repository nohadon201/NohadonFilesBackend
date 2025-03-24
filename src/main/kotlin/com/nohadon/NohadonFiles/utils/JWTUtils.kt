package com.nohadon.NohadonFiles.utils

import com.nohadon.NohadonFiles.core.entities.DTO.TokenDTO
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import java.nio.charset.Charset
import java.util.*


class JWTUtils (
    @Value("\${ADMIN_USR_NF}") val userName : String
) {
    companion object {
        const val SECRET_KEY: String = "your-secret-key" // This should be kept secure
        const val EXPIRATION_TIME: Long = 864000000 // 10 days in milliseconds
    }

    fun generateToken(UUID: String?): TokenDTO {
        val key = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray())

        return TokenDTO(
            Jwts.builder()
                .subject(UUID)
                .expiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact()
        )
    }

    //fun extractUUIDOfUser(token: String?): String {
    //    return Jwts.parser()
    //}

    //fun validateToken(token: String?): Boolean {
    //    try {
    //        Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
    //        return true
    //    } catch (e: Exception) {
    //        return false
    //    }
    //}
}