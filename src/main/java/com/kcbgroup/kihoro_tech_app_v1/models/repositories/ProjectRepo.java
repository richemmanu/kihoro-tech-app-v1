package com.kcbgroup.kihoro_tech_app_v1.models.repositories;

import com.kcbgroup.kihoro_tech_app_v1.models.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
