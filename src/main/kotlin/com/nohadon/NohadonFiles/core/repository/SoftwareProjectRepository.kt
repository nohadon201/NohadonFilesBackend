package com.nohadon.NohadonFiles.core.repository

import com.nohadon.NohadonFiles.core.entities.FilterSoftProject
import com.nohadon.NohadonFiles.core.entities.Project
import com.nohadon.NohadonFiles.core.entities.SoftwareProject
import org.springframework.data.jpa.repository.JpaRepository

interface SoftwareProjectRepository : JpaRepository<SoftwareProject, Long> {}