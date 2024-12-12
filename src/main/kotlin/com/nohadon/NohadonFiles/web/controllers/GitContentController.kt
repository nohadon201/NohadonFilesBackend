package com.nohadon.NohadonFiles.web.controllers

import com.nohadon.NohadonFiles.core.model.GitDirectory
import com.nohadon.NohadonFiles.core.services.GithubService
import com.nohadon.NohadonFiles.exceptions.GitErrorResponseException
import com.nohadon.NohadonFiles.web.WebConstants
import jakarta.websocket.server.PathParam
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/gitContent")
class GitContentController (
    private val githubService: GithubService
) {

    @GetMapping("/get{projectName}")
    fun getProject(@PathParam("projectName") projectName : String) : ResponseEntity<GitDirectory> {
        return try {
            val projectDirectory : GitDirectory = githubService.getDirectory(projectName, "/")
            ResponseEntity.status(HttpStatus.OK)
                .header(WebConstants.CORS_HEADER, WebConstants.CORS_HEADER_VALUE)
                .body(projectDirectory);
        } catch (e: GitErrorResponseException) {
            log.error(e.stackTraceToString())
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(WebConstants.CORS_HEADER, WebConstants.CORS_HEADER_VALUE)
                .build()
        } catch (e : Exception) {
            log.error(e.stackTraceToString())
            ResponseEntity.status(HttpStatus.CONFLICT)
                .header(WebConstants.CORS_HEADER, WebConstants.CORS_HEADER_VALUE)
                .build()
        }
    }

    @GetMapping("/readFile{projectName}{filePath}")
    fun getFile(@PathParam("projectName") projectName : String, @PathParam("filePath") filePath : String) : ResponseEntity<String> {
        return try {
            val fileContent : String = githubService.getFile(projectName, filePath)
            ResponseEntity.status(HttpStatus.OK)
                .header(WebConstants.CORS_HEADER, WebConstants.CORS_HEADER_VALUE)
                .header("responseType", "text")
                .body(fileContent);
        } catch (e: GitErrorResponseException) {
            log.error(e.stackTraceToString())
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(WebConstants.CORS_HEADER, WebConstants.CORS_HEADER_VALUE)
                .body("There's a problem with the connection from the backend to the Github Api. Please try later.")
        } catch (e : Exception) {
            log.error(e.stackTraceToString())
            ResponseEntity.status(HttpStatus.CONFLICT)
                .header(WebConstants.CORS_HEADER, WebConstants.CORS_HEADER_VALUE)
                .body(e.stackTraceToString())
        }
    }
    
    companion object  {
        val log : Logger = LoggerFactory.getLogger(GitContentController::class.java)
    }

}