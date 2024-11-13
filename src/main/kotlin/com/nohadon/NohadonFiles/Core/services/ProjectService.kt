package com.nohadon.NohadonFiles.Core.services

import com.nohadon.NohadonFiles.Core.model.Project
import com.nohadon.NohadonFiles.Core.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService constructor(
    private val projectRepository : ProjectRepository
) {
    fun getAll(list : MutableList<Project>) {
        list.addAll(projectRepository.findAll())
    }
    fun createProject(project : Project) {
        projectRepository.save(project)
    }
}