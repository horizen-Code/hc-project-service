package com.horizen.hc_project_service.controller.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.horizen.hc_project_service.controller.ProjectController;
import com.horizen.hc_project_service.dto.CreateProjectRequest;
import com.horizen.hc_project_service.dto.UpdateProjectRequest;
import com.horizen.hc_project_service.model.Project;
import com.horizen.hc_project_service.service.ProjectService;

@RestController
public class ProjectControllerImpl implements ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectControllerImpl.class);

    private final ProjectService projectService;

    public ProjectControllerImpl(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody CreateProjectRequest request) {
        Project createdProject = projectService.createProject(request);
        return ResponseEntity.status(201).body(createdProject);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Project>> getAllPublicProjects() {
        List<Project> projects = projectService.getAllPublicProjects();
        logger.info("Returned {} public projects", projects.size());
        return ResponseEntity.ok(projects);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID id) {
        Optional<Project> project = projectService.getProjectById(id);
        logger.info("Returned projects by id {}", id);
        return project.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/byName")
    public ResponseEntity<Project> getProjectByName(String name) {
        Optional<Project> project = projectService.getProjectByName(name);
        logger.info("Returned projects by name {}", name);
        return project.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable UUID id, @RequestBody UpdateProjectRequest request,
            boolean partial) {
        Project updatedProject = new Project();
        updatedProject.setName(request.getName());
        updatedProject.setAuthorId(request.getAuthorId());
        updatedProject.setTeamId(request.getTeamId());
        updatedProject.setVisibility(request.getVisibility());

        Project result = projectService.updateProject(id, updatedProject, partial);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        logger.info("Deleted project with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/transfer/{id}")
    public ResponseEntity<Void> transferProject(@PathVariable UUID projectId, @RequestParam UUID newOwnerId) {
        projectService.transferProjectToUser(projectId, newOwnerId);
        logger.info("Transferred project {} to user {}", projectId, newOwnerId);
        return ResponseEntity.noContent().build();
    }

}
