package com.nohadon.NohadonFiles.core.services

import com.nohadon.NohadonFiles.core.model.DTO.GitObjectDTO
import com.nohadon.NohadonFiles.core.model.GitDirectory
import com.nohadon.NohadonFiles.core.model.GitFile
import com.nohadon.NohadonFiles.exceptions.GitErrorResponseException
import com.nohadon.NohadonFiles.exceptions.NullBodyResponseException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class GithubService (
    @Value("\${GIT_TOKEN}") private val personalGitToken : String,
    @Value("\${GIT_URL}") private val githubUrl : String,
    private val projectService: ProjectService
) {
    private var githubClient: RestClient = RestClient.builder()
        .baseUrl(githubUrl)
        .defaultHeader(H_AUTHORIZATION, "Bearer $personalGitToken")
        .defaultHeader(H_ACCEPT, HV_ACCEPT)
        .defaultHeader(H_GIT_API_VERSION, HV_GIT_API_VERSION)
        .build()

    fun getFile(id : Long, path : String) : String {
        val project = projectService.get(id)
        val result = githubClient.get().uri("$githubUrl/${project.githubProjectName}/contents${project.defaultPath}$path")
            .retrieve()
            .onStatus(HttpStatusCode::isError) {
                _, response ->

            throw GitErrorResponseException("file", path, response.statusText)
        }.onStatus(HttpStatusCode::is3xxRedirection){
                _, response ->
            println("REDIRECTION: ${response.body}")
        }.body<String>()
        return result?:""
    }
    fun getDirectory(id: Long, currentPath : String) : GitDirectory {
        val project = projectService.get(id)
        val list : MutableList<GitObjectDTO> = mutableListOf();
        var result = githubClient.get()
            .uri("$githubUrl/${project.githubProjectName}/contents${project.defaultPath}$currentPath")
            .retrieve()
            .onStatus(HttpStatusCode::is3xxRedirection) {
                    _, response ->
                val newUrl = response.headers["location"]!![0]
                redirect(newUrl, list)
            }.onStatus(HttpStatusCode::isError) {
                    _, response ->
               throw GitErrorResponseException("directory", currentPath, response.statusText)
            }.body<MutableList<GitObjectDTO>>()


        result = result?: list;

        val directories: MutableList<GitDirectory> = mutableListOf()
        val files: MutableList<GitFile> = mutableListOf()

        result.forEach {
            if (FILE_TYPE == it.getType()) {
                files.add(GitFile(it.getName(), currentPath+it.getName(), it.getSize().toLong()))
            } else if (DIR_TYPE == it.getType()) {
                val subDirectory = getDirectory(id, "$currentPath${it.getName()}/")
                directories.add(subDirectory)
            }
        }

        var nameOfDirectory : String = if(currentPath.length>1) currentPath.substring(1,currentPath.length-1) else ""
        if(nameOfDirectory.isNotEmpty()) nameOfDirectory = nameOfDirectory.split("/").last()

        return GitDirectory(nameOfDirectory, currentPath, directories, files)
    }
    fun redirect(url : String, list : MutableList<GitObjectDTO>) {
        val a = githubClient.get()
            .uri(url)
            .retrieve()
            .onStatus(HttpStatusCode::is3xxRedirection) {
                    _, response ->
                val newUrl = response.headers["location"]!![0]
                redirect(newUrl, list)
            }.onStatus(HttpStatusCode::isError) {
                    _, response ->
                throw GitErrorResponseException("redirect", url, response.statusText)
            }.body<MutableList<GitObjectDTO>>() ?: throw NullBodyResponseException("directory", url);
        list.addAll(a)
    }
    companion object {
        val log: Logger = LoggerFactory.getLogger(GithubService::class.java)
        const val H_ACCEPT = "Accept"
        const val HV_ACCEPT = "application/vnd.github.raw+json"
        const val H_AUTHORIZATION = "Authorization"
        const val H_GIT_API_VERSION = "X-GitHub-Api-Version"
        const val HV_GIT_API_VERSION = "2022-11-28"
        const val FILE_TYPE = "file"
        const val DIR_TYPE = "dir"
    }
}