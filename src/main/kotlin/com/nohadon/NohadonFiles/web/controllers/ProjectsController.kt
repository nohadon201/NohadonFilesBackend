package com.nohadon.NohadonFiles.web.controllers


import com.nohadon.NohadonFiles.core.model.DTO.ProjectDTO
import com.nohadon.NohadonFiles.core.model.GitDirectory
import com.nohadon.NohadonFiles.core.model.Project
import com.nohadon.NohadonFiles.core.services.GithubService
import com.nohadon.NohadonFiles.core.services.ProjectService
import com.nohadon.NohadonFiles.exceptions.GitErrorResponseException
import jakarta.websocket.server.PathParam
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
    private val githubService: GithubService
) {
    @PostMapping("/createProject")
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

        projectService.createProject(p)

        return ResponseEntity.status(HttpStatus.OK).body("The project was created successfully.")
    }

    @GetMapping("/getAll")
    fun getAll() : ResponseEntity<List<Project>> {
        val list : MutableList<Project> = mutableListOf()
        projectService.getAll(list);
        return ResponseEntity.status(HttpStatus.OK).header(CORS_HEADER, CORS_HEADER_VALUE).body(list);
    }
    @GetMapping("/getProject{projectName}")
    fun getProject(@PathParam("projectName") projectName : String) : ResponseEntity<GitDirectory> {
        return try {
            val projectDirectory : GitDirectory = githubService.getDirectory(projectName, "/")
            ResponseEntity.status(HttpStatus.OK).header(CORS_HEADER, CORS_HEADER_VALUE).body(projectDirectory);
        } catch (e: GitErrorResponseException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(CORS_HEADER, CORS_HEADER_VALUE).build()
        } catch (e : Exception) {
            ResponseEntity.status(HttpStatus.CONFLICT).header(CORS_HEADER, CORS_HEADER_VALUE).build()
        }
    }


    @GetMapping("/getFile{projectName}{filePath}")
    fun getFile(@PathParam("projectName") projectName : String, @PathParam("filePath") filePath : String) : ResponseEntity<String> {
        return try {
            val fileContent : String = githubService.getFile(projectName, filePath)
            ResponseEntity.status(HttpStatus.OK).header(CORS_HEADER, CORS_HEADER_VALUE).
                header("responseType", "text").body(fileContent);
        } catch (e: GitErrorResponseException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(CORS_HEADER, CORS_HEADER_VALUE).body("There's a problem with the connection from the backend to the Github Api. Please try later.")
        } catch (e : Exception) {
            ResponseEntity.status(HttpStatus.CONFLICT).header(CORS_HEADER, CORS_HEADER_VALUE).body(e.stackTraceToString())
        }
    }

    companion object {
        const val CORS_HEADER : String = "Access-Control-Allow-Origin"
        const val CORS_HEADER_VALUE : String = "*"
    }

}