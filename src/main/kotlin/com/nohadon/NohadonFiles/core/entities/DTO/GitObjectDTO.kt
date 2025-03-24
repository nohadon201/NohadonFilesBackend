package com.nohadon.NohadonFiles.core.entities.DTO

import org.jetbrains.annotations.NotNull
import java.io.Serializable

class GitObjectDTO  constructor(
    @NotNull
    private val name : String,
    @NotNull
    private val type : String,
    @NotNull
    private val size : String
) : Serializable {
    fun getName() = name
    fun getType() = type
    fun getSize() = size
}