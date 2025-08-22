package com.nohadon.NohadonFiles.core.repository

import com.nohadon.NohadonFiles.core.model.entities.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long> {}