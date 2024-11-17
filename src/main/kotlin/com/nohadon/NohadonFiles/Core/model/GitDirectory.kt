package com.nohadon.NohadonFiles.Core.model

class GitDirectory (
    val nameDirectory: String = "",
    val directories : MutableList<GitDirectory> = mutableListOf(),
    val files : MutableList<GitFile> = mutableListOf()
)