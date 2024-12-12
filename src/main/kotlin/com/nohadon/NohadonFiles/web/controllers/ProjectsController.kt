package com.nohadon.NohadonFiles.web.controllers


import com.nohadon.NohadonFiles.core.model.DTO.ProjectDTO
import com.nohadon.NohadonFiles.core.model.Project
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
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/projects")
class ProjectsController constructor(
    private val projectService : ProjectService,
) {
    @PostMapping("/create")
    fun create(@RequestBody @Validated projectDTO : ProjectDTO) : ResponseEntity<String> {
        val p : Project = Project(
            projectDTO.getTitle(),
            projectDTO.getLanguages(),
            projectDTO.getInProgress(),
            projectDTO.getIcon(),
            projectDTO.getColor(),
            projectDTO.getGithub(),
            projectDTO.getDescription()
        );
        return try {
            projectService.createProject(p)
            ResponseEntity.status(HttpStatus.OK).body("The project was created successfully.")
        } catch (ex : Exception) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(ex.stackTraceToString())
        }

    }

    @GetMapping("/getAll")
    fun getAll() : ResponseEntity<List<Project>> {
        val list : MutableList<Project> = mutableListOf()
        return try {
            projectService.getAll(list);
            ResponseEntity.status(HttpStatus.OK)
                .header(WebConstants.CORS_HEADER, WebConstants.CORS_HEADER_VALUE)
                .body(list)
        } catch (e : Exception) {
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
            ResponseEntity.status(HttpStatus.CONFLICT).body(-1);
        } catch (e : Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-4);
        }
    }

}