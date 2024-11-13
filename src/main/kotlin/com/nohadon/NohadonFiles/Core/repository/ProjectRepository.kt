package com.nohadon.NohadonFiles.Core.repository

import com.nohadon.NohadonFiles.Core.model.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long> {}