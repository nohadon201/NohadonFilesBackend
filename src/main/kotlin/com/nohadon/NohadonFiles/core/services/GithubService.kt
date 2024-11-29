package com.nohadon.NohadonFiles.core.services

import com.nohadon.NohadonFiles.core.model.DTO.GitObjectDTO
import com.nohadon.NohadonFiles.core.model.GitDirectory
import com.nohadon.NohadonFiles.core.model.GitFile
import com.nohadon.NohadonFiles.exceptions.GitErrorResponseException
import com.nohadon.NohadonFiles.exceptions.NullBodyResponseException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class GithubService (
    @Value("\${GIT_TOKEN}") private val personalGitToken : String,
    @Value("\${GIT_URL}") private val githubUrl : String,
) {
    private var githubClient: RestClient = RestClient.builder()
        .baseUrl(githubUrl)
        .defaultHeader(H_AUTHORIZATION, "Bearer $personalGitToken")
        .defaultHeader(H_ACCEPT, HV_ACCEPT)
        .defaultHeader(H_GIT_API_VERSION, HV_GIT_API_VERSION)
        .build()
    fun getFile(projectName : String, path : String) : String {
        val result = githubClient.get().uri("$githubUrl/$projectName/contents$path").retrieve().onStatus(HttpStatusCode::isError){
                _, response ->

            throw GitErrorResponseException("file", path, response.statusText)
        }.onStatus(HttpStatusCode::is3xxRedirection){
                _, response ->
            println("REDIRECTION: ${response.body}")
        }.body<String>()
        return result?:""
    }
    fun getDirectory(projectName: String, currentDirectory : String) : GitDirectory {
        val list : MutableList<GitObjectDTO> = mutableListOf();
        var result = githubClient.get()
            .uri("$githubUrl/$projectName/contents$currentDirectory")
            .retrieve()
            .onStatus(HttpStatusCode::is3xxRedirection) {
                    _, response ->
                val newUrl = response.headers["location"]!![0]
                redirect(newUrl, list)
            }.onStatus(HttpStatusCode::isError) {
                    _, response ->
               throw GitErrorResponseException("directory", currentDirectory, response.statusText)
            }.body<MutableList<GitObjectDTO>>()


        result = result?: list;

        val directories: MutableList<GitDirectory> = mutableListOf()
        val files: MutableList<GitFile> = mutableListOf()

        result.forEach {
            if (FILE_TYPE == it.getType()) {
                files.add(GitFile(it.getName(), currentDirectory+it.getName(), it.getSize().toLong()))
            } else if (DIR_TYPE == it.getType()) {
                val subDirectory = getDirectory(projectName, "$currentDirectory${it.getName()}/")
                directories.add(subDirectory)
            }
        }

        return GitDirectory(currentDirectory, directories, files)
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
        const val H_ACCEPT = "Accept"
        const val HV_ACCEPT = "application/vnd.github.raw+json"
        const val H_AUTHORIZATION = "Authorization"
        const val H_GIT_API_VERSION = "X-GitHub-Api-Version"
        const val HV_GIT_API_VERSION = "2022-11-28"
        const val FILE_TYPE = "file"
        const val DIR_TYPE = "dir"
    }
}