package com.nohadon.NohadonFiles.core.model

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
    var githubProjectName:String = "",

    @Column(name="description", nullable = false)
    var description:String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    var id: Long? = null,
) {}