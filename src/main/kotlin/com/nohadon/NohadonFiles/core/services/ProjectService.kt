package com.nohadon.NohadonFiles.core.services

import com.nohadon.NohadonFiles.core.model.Project
import com.nohadon.NohadonFiles.core.repository.ProjectRepository
import com.nohadon.NohadonFiles.exceptions.InvalidIdException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProjectService constructor(
    private val projectRepository : ProjectRepository,
) {
    fun getAll(list : MutableList<Project>) {
        list.addAll(projectRepository.findAll())
    }
    fun createProject(project : Project) {
        projectRepository.save(project)
    }
    fun deleteProject(id : Long) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id)
        } else {
            throw InvalidIdException(id)
        }
    }
    fun get(id : Long) : Project {
        return projectRepository.getReferenceById(id);
    }
    companion object {
        val log : Logger = LoggerFactory.getLogger(ProjectService::class.java)
    }
}