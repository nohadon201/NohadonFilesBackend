package com.nohadon.NohadonFiles.core.entities

import com.nohadon.NohadonFiles.core.entities.types.Repository
import jakarta.persistence.*

@MappedSuperclass
open class Project() {
    @Column(name = "title", nullable = false, unique = true)
    var title: String = ""

    @Column(name = "subTitle", nullable = false)
    var subTitle: String = ""

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

    constructor (
        title: String,
        subTitle: String,
        inProgress: Boolean,
        icon: String,
        color: String,
        githubProjectName: String,
        description:String,
        defaultPath : String
    ) : this() {
        this.title = title
        this.subTitle = subTitle
        this.inProgress = inProgress
        this.icon = icon
        this.color = color
        this.githubProjectName = githubProjectName
        this.description = description
        this.defaultPath = defaultPath
        this.id = null
    }

    override fun toString(): String {
        return "Project(title='$title', subTitle='$subTitle', inProgress=$inProgress, icon='$icon', color='$color', githubProjectName='$githubProjectName', description='$description', defaultPath='$defaultPath', id=$id)"
    }
}

@Entity
@Table(name = "SoftwareProject")
class SoftwareProject(
    title: String,
    subTitle: String,
    @Column(name = "filters")
    @OneToMany
    var filters : Set<FilterSoftProject>,
    inProgress: Boolean,
    icon: String,
    color: String,
    githubProjectName: String,
    description:String,
    defaultPath : String
) : Project(title, subTitle, inProgress, icon, color, githubProjectName, description, defaultPath) {
    override fun toString(): String {
        return "SoftwareProject(filters=$filters) ${super.toString()}"
    }
}

@Entity
@Table(name = "GameProject")
class GameProject (
    title: String,
    subTitle: String,
    @Column(name = "filters")
    @OneToMany
    var filters : Set<FilterGameProject>,
    inProgress: Boolean,
    icon: String,
    color: String,
    githubProjectName: String,
    description:String,
    defaultPath : String
) : Project(title, subTitle, inProgress, icon, color, githubProjectName, description, defaultPath) {
    override fun toString(): String {
        return "GameProject(filters=$filters) ${super.toString()}"
    }
}