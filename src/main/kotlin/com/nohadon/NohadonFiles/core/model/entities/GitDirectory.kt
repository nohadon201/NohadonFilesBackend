package com.nohadon.NohadonFiles.core.model.entities

class GitDirectory (
    val nameDirectory: String = "",
    val fullPath: String = "",
    val directories : MutableList<GitDirectory> = mutableListOf(),
    val files : MutableList<GitFile> = mutableListOf()
)