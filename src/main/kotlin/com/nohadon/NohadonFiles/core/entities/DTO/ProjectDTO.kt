package com.nohadon.NohadonFiles.core.entities.DTO

import com.nohadon.NohadonFiles.core.entities.Filter
import com.nohadon.NohadonFiles.core.entities.FilterSoftProject
import org.springframework.lang.NonNull

open class ProjectDTO (
    @NonNull private val title: String = "",
    @NonNull private val subTitle: String = "",
    @NonNull private val githubProjectName: String = "",
    @NonNull private val description : String = "",
    private val icon: String?,
    private val inProgress: Boolean?,
    private val color: String?,
    private val defaultPath: String?
) {
    fun getTitle() : String = this.title
    fun getSubtitle() : String = this.subTitle
    fun getGithub() : String = this.githubProjectName
    fun getDescription() : String = this.description
    fun getInProgress() : Boolean = this.inProgress?:true;
    fun getIcon() : String = this.icon?:"\uF413";
    fun getColor() : String = this.color?:"purple"
    fun getDefaultPath() : String = this.defaultPath?:""
}

class SoftwareProjectDTO(
    title : String,
    subTitle : String,
    @NonNull private val filters : Set<FilterSoftProjectDTO>,
    githubProjectName : String,
    description : String,
    icon : String?,
    inProgress : Boolean?,
    color : String?,
    defaultPath : String?,
) : ProjectDTO(
    title,
    subTitle,
    githubProjectName,
    description,
    icon,
    inProgress,
    color,
    defaultPath
) {
    fun getFilters() : Set<FilterSoftProjectDTO> = filters
}
