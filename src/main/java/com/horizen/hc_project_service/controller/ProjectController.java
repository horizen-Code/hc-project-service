package com.horizen.hc_project_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.horizen.hc_project_service.dto.CreateProjectRequest;
import com.horizen.hc_project_service.dto.UpdateProjectRequest;
import com.horizen.hc_project_service.model.Project;

import java.util.List;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
public interface ProjectController {

    ResponseEntity<Project> createProject(CreateProjectRequest request);

    ResponseEntity<List<Project>> getAllPublicProjects();

    ResponseEntity<Project> getProjectById(@PathVariable UUID id);

    ResponseEntity<Project> getProjectByName(@RequestParam String name);

    ResponseEntity<Project> updateProject(UUID id, UpdateProjectRequest request,
            @RequestParam(defaultValue = "false") boolean partial);

    ResponseEntity<Void> deleteProject(UUID id);

    ResponseEntity<Void> transferProject(@PathVariable UUID projectId, @RequestParam UUID newOwnerId);
}
