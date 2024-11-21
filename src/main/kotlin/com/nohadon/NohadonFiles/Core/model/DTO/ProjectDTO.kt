package com.nohadon.NohadonFiles.Core.model.DTO

import org.jetbrains.annotations.NotNull

class ProjectDTO constructor(
    private val id: Long,
    @NotNull
    private val title: String = "",
    @NotNull
    private val languages: String = "",
    @NotNull
    private val inProgress: Boolean = true,
    @NotNull
    private val icon: String = "",
    @NotNull
    private val color: String = "purple",
    @NotNull
    private val githubProjectName: String = "",
    @NotNull
    private val description : String = ""
) {
    fun getId() : Long = this.id
    fun getTitle() : String = this.title
    fun getLanguages() : String = this.languages
    fun getInProgress() : Boolean = this.inProgress
    fun getIcon() : String = this.icon
    fun getColor() : String = this.color
    fun getGithub() : String = this.githubProjectName
    fun getDescription() : String = this.description

}