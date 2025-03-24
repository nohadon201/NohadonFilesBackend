package com.nohadon.NohadonFiles.core.entities

import com.nohadon.NohadonFiles.core.entities.types.CategoryFilterGameDevelopment
import com.nohadon.NohadonFiles.core.entities.types.CategoryFilterSofwareDevelopment
import jakarta.persistence.*

@MappedSuperclass
open class Filter() {
    @Id
    @Column(name = "value")
    var value : String = ""

    constructor( value : String) : this() {
        this.value = value
    }
}

@Entity
@Table(name = "FilterSoftProject")
class FilterSoftProject(
    @Id
    @Column(name = "category", nullable = false)
    var category : CategoryFilterSofwareDevelopment = CategoryFilterSofwareDevelopment.PROJECT_SIZE,
    value : String
) : Filter(value) {

}

@Entity
@Table(name = "FilterGameProject")
class FilterGameProject(
    @Id
    @Column(name = "category", nullable = false)
    var category : CategoryFilterGameDevelopment = CategoryFilterGameDevelopment.GAME_CATEGORY,
    value : String
) : Filter(value) { }
