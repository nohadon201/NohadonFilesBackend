package com.nohadon.NohadonFiles.core.services

import com.nohadon.NohadonFiles.core.model.Project
import com.nohadon.NohadonFiles.core.repository.ProjectRepository
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