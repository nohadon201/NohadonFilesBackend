package com.nohadon.NohadonFiles.core.services

import com.nohadon.NohadonFiles.core.entities.SoftwareProject
import com.nohadon.NohadonFiles.core.repository.SoftwareProjectRepository
import com.nohadon.NohadonFiles.exceptions.InvalidIdException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProjectService constructor(
    private val softwareProjectRepository : SoftwareProjectRepository,
) {
    fun getAllSoftwareProjects(list : MutableList<SoftwareProject>) {
        list.addAll(softwareProjectRepository.findAll())
    }
    fun createProjectSofwareProject(project : SoftwareProject) {
        softwareProjectRepository.save(project)
    }
    fun deleteProject(id : Long) {
        if (softwareProjectRepository.existsById(id)) {
            softwareProjectRepository.deleteById(id)
        } else {
            throw InvalidIdException(id)
        }
    }
    fun getSoftwareProject(id : Long) : SoftwareProject {
        return softwareProjectRepository.getReferenceById(id);
    }
    companion object {
        val log : Logger = LoggerFactory.getLogger(ProjectService::class.java)
    }
}