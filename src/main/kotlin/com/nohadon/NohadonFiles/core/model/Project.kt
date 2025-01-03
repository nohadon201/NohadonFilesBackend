package com.nohadon.NohadonFiles.core.model

import jakarta.persistence.*
@Table(name="Project")
@Entity
class Project () {
    @Column(name = "title", nullable = false, unique = true)
    var title: String = ""

    @Column(name = "subTitle", nullable = false)
    var subTitle: String = ""

    @Column(name = "languages", nullable = false)
    var languages: String = ""

    @Column(name = "inProgress", nullable = false)
    var inProgress: Boolean = true

    @Column(name = "icon", nullable = false)
    var icon: String = ""

    @Column(name = "color", nullable = false)
    var color: String = "purple"

    @Column(name="github", nullable = false)
    var githubProjectName: String = ""

    @Column(name="description", nullable = false, columnDefinition = "TEXT")
    var description:String = ""

    @Column(name = "defaultPath", nullable = false)
    var defaultPath : String = ""

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    var id: Long? = null

    constructor(
        title: String,
        subTitle: String,
        languages: String,
        inProgress: Boolean,
        icon: String,
        color: String,
        githubProjectName: String,
        description:String,
        defaultPath : String
    ) : this() {
        this.title = title
        this.subTitle = subTitle
        this.languages = languages
        this.inProgress = inProgress
        this.icon = icon
        this.color = color
        this.githubProjectName = githubProjectName
        this.description = description
        this.defaultPath = defaultPath
        this.id = null
    }
    override fun toString(): String {
        return "Project(title='$title', subTitle='$subTitle', languages='$languages', inProgress=$inProgress, icon='$icon', color='$color', githubProjectName='$githubProjectName', description='$description', defaultPath='$defaultPath', id=$id)"
    }

}