package com.nohadon.NohadonFiles.web.controllers


import com.nohadon.NohadonFiles.core.entities.DTO.ProjectDTO
import com.nohadon.NohadonFiles.core.entities.DTO.SoftwareProjectDTO
import com.nohadon.NohadonFiles.core.entities.FilterGameProject
import com.nohadon.NohadonFiles.core.entities.FilterSoftProject
import com.nohadon.NohadonFiles.core.entities.Project
import com.nohadon.NohadonFiles.core.entities.SoftwareProject
import com.nohadon.NohadonFiles.core.entities.types.Repository
import com.nohadon.NohadonFiles.core.services.ProjectService
import com.nohadon.NohadonFiles.exceptions.InvalidIdException
import com.nohadon.NohadonFiles.web.WebConstants
import jakarta.websocket.server.PathParam
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/projects")
class ProjectsController constructor(
    private val projectService : ProjectService,
) {
    @PostMapping("/createSD")
    fun createSoftwareDevelopment(
        @RequestBody @Validated projectDTO : SoftwareProjectDTO
    ) : ResponseEntity<String> {
        val p = SoftwareProject(
            projectDTO.getTitle(),
            projectDTO.getSubtitle(),
            projectDTO.getFilters(),
            projectDTO.getInProgress(),
            projectDTO.getIcon(),
            projectDTO.getColor(),
            projectDTO.getGithub(),
            projectDTO.getDescription(),
            projectDTO.getDefaultPath()
        );
        return try {
            projectService.createProjectSofwareProject(p)
            ResponseEntity.status(HttpStatus.OK).body("The project was created successfully.")
        } catch (ex : Exception) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(ex.stackTraceToString())
        }

    }
    //@PostMapping("/createGD")
    //fun createGameDevelopment(
    //    @RequestBody @Validated projectDTO : ProjectDTO<FilterGameProject>
    //) : ResponseEntity<String> {
    //    val p = Project(
    //        projectDTO.getTitle(),
    //        projectDTO.getSubtitle(),
    //        projectDTO.getFilters(),
    //        Repository.GameDevelopment,
    //        projectDTO.getInProgress(),
    //        projectDTO.getIcon(),
    //        projectDTO.getColor(),
    //        projectDTO.getGithub(),
    //        projectDTO.getDescription(),
    //        projectDTO.getDefaultPath()
    //    );
    //    return try {
    //        projectService.createProjectSofwareProject(p)
    //        ResponseEntity.status(HttpStatus.OK).body("The project was created successfully.")
    //    } catch (ex : Exception) {
    //        ResponseEntity.status(HttpStatus.CONFLICT).body(ex.stackTraceToString())
    //    }
//
    //}

    @GetMapping("/getAllSoftwareProjects")
    fun getAll() : ResponseEntity<List<SoftwareProject>> {
        val list : MutableList<SoftwareProject> = mutableListOf()
        return try {
            projectService.getAllSoftwareProjects(list);
            ResponseEntity.status(HttpStatus.OK)
                .header(WebConstants.CORS_HEADER, WebConstants.CORS_HEADER_VALUE)
                .body(list)
        } catch (e : Exception) {
            log.error("Error in /getAll endpoint: ${e.stackTraceToString()}")
            ResponseEntity.status(HttpStatus.CONFLICT)
                .header(WebConstants.CORS_HEADER, WebConstants.CORS_HEADER_VALUE)
                .header(WebConstants.ERR_MSG_HEADER, e.message)
                .body(mutableListOf())
        }
    }

    @GetMapping("/delete{id}")
    fun delete (
        @PathParam("id") id : Long
    ) : ResponseEntity<Long> {
        return try {
            projectService.deleteProject(id)
            ResponseEntity.status(HttpStatus.OK).body(4);
        } catch (e : InvalidIdException) {
            log.error("Error in /delete endpoint: ${e.stackTraceToString()}")
            ResponseEntity.status(HttpStatus.CONFLICT).body(-1);
        } catch (e : Exception) {
            log.error("Error in /delete endpoint: ${e.stackTraceToString()}")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-4);
        }
    }

    companion object {
        val log : Logger = LoggerFactory.getLogger(ProjectsController::class.java)
    }

}