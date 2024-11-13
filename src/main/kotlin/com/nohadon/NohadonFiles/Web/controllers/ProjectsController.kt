package com.nohadon.NohadonFiles.Web.controllers


import com.nohadon.NohadonFiles.Core.model.DTO.ProjectDTO
import com.nohadon.NohadonFiles.Core.model.Project
import com.nohadon.NohadonFiles.Core.services.ProjectService
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.EntityResponse


@RestController
@RequestMapping("/projects")
class ProjectsController constructor(
    private val projectService : ProjectService
) {
    @PostMapping("/createProject")
    fun create(@RequestBody @Validated projectDTO : ProjectDTO) : ResponseEntity<String> {
        val p : Project = Project(
            projectDTO.getTitle(),
            projectDTO.getLanguages(),
            projectDTO.getInProgress(),
            projectDTO.getIcon(),
            projectDTO.getColor()
        );

        projectService.createProject(p)

        return ResponseEntity.status(HttpStatus.OK).body("The project was created successfully.")
    }

    @GetMapping("/getAll")
    fun getAll() : ResponseEntity<List<Project>> {
        val list : MutableList<Project> = mutableListOf()
        projectService.getAll(list);
        return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*").body(list);
    }
}