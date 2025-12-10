package com.horizen.hc_project_service.service;

import java.util.List;
import java.util.UUID;

import com.horizen.hc_project_service.dto.CreateProjectRequest;
import com.horizen.hc_project_service.model.Project;

import java.util.Optional;

public interface ProjectService {
    Project createProject(CreateProjectRequest request);

    List<Project> getAllPublicProjects();

    Optional<Project> getProjectById(UUID id);

    Optional<Project> getProjectByName(String name);

    Project updateProject(UUID id, Project updatedProject, boolean isPartial);

    void deleteProject(UUID id);

    void transferProjectToUser(UUID projectId, UUID newOwnerId);
}
