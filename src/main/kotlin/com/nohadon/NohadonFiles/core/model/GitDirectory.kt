package com.nohadon.NohadonFiles.core.model

class GitDirectory (
    val nameDirectory: String = "",
    val directories : MutableList<GitDirectory> = mutableListOf(),
    val files : MutableList<GitFile> = mutableListOf()
)