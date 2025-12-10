package com.horizen.hc_project_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.horizen.hc_project_service.model.Project;
import com.horizen.hc_project_service.model.Visibility;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    List<Project> findByVisibility(Visibility visibility);

    Optional<Project> findById(UUID id);

    Optional<Project> findByName(String name);
}