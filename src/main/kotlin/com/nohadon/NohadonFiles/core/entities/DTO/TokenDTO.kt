package com.nohadon.NohadonFiles.core.entities.DTO

import org.jetbrains.annotations.NotNull
import jakarta.validation.constraints.NotBlank

class TokenDTO (
    @NotBlank private var token : String
) {
    fun getToken() : String = this.token
    fun getToken(token : String) { this.token = token }
}