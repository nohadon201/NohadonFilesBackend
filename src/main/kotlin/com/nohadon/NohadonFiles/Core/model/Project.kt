package com.nohadon.NohadonFiles.Core.model

import com.nohadon.NohadonFiles.Core.model.DTO.ProjectDTO
import jakarta.persistence.*

@Entity
class Project (

    @Column(name = "title", nullable = false, unique = true)
    var title: String = "",

    @Column(name = "languages", nullable = false)
    var languages: String = "",

    @Column(name = "inProgress", nullable = false)
    var inProgress: Boolean = true,

    @Column(name = "icon", nullable = false)
    var icon: String = "",

    @Column(name = "color", nullable = false)
    var color: String = "purple",

    @Column(name="github", nullable = false)
    var githubUrl:String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    var id: Long? = null,
) {}