package com.nohadon.NohadonFiles.core.model.DTO

import org.springframework.lang.NonNull

class   ProjectDTO (
    @NonNull private val title: String = "",
    @NonNull private val subTitle: String = "",
    @NonNull private val languages: String = "",
    @NonNull private val githubProjectName: String = "",
    @NonNull private val description : String = "",
    private val icon: String?,
    private val inProgress: Boolean?,
    private val color: String?,
    private val defaultPath:String?
) {
    fun getTitle() : String = this.title
    fun getSubtitle() : String = this.subTitle
    fun getLanguages() : String = this.languages
    fun getGithub() : String = this.githubProjectName
    fun getDescription() : String = this.description
    fun getInProgress() : Boolean = this.inProgress?:true;
    fun getIcon() : String = this.icon?:"\uF413";
    fun getColor() : String = this.color?:"purple"
    fun getDefaultPath() : String = this.defaultPath?:""
}