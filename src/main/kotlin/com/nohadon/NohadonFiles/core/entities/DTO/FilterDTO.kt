package com.nohadon.NohadonFiles.core.entities.DTO

import com.nohadon.NohadonFiles.core.entities.types.CategoryFilterGameDevelopment
import com.nohadon.NohadonFiles.core.entities.types.CategoryFilterSofwareDevelopment
import org.jetbrains.annotations.NotNull

class FilterSoftProjectDTO (
    @NotNull private val category : CategoryFilterSofwareDevelopment = CategoryFilterSofwareDevelopment.PROJECT_SIZE,
    @NotNull private val value : String
) {
    fun getCategory() : CategoryFilterSofwareDevelopment = category
    fun getValue() : String = value
}

class FilterGameProjectDTO (
    @NotNull private val category : CategoryFilterGameDevelopment = CategoryFilterGameDevelopment.GAME_CATEGORY,
    @NotNull private val value : String
) {
    fun getCategory() : CategoryFilterGameDevelopment = category
    fun getValue() : String = value
}
