package com.horizen.hc_project_service.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.horizen.hc_project_service.model.Project;
import com.horizen.hc_project_service.model.Visibility;
import com.horizen.hc_project_service.repository.ProjectRepository;
import com.horizen.hc_project_service.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllPublicProjects() {
        logger.info("Fetching all public projects");
        return projectRepository.findByVisibility(Visibility.PUBLIC);
    }

    @Override
    public Optional<Project> getProjectById(UUID id) {
        logger.info("Fetching project by ID: {}", id);
        return projectRepository.findById(id);
    }

    @Override
    public Optional<Project> getProjectByName(String name) {
        logger.info("Fetching project by name: {}", name);
        return projectRepository.findByName(name);
    }

    // TODO: refactor

    @Override
    public Project updateProject(UUID id, Project updatedProject, boolean isPartial) {
        Optional<Project> existingProjectOpt = projectRepository.findById(id);
        if (existingProjectOpt.isPresent()) {
            Project existingProject = existingProjectOpt.get();
            logger.info("Updating project with ID: {}", id);

            if (!isPartial) {
                existingProject.setName(updatedProject.getName());
                existingProject.setAuthorId(updatedProject.getAuthorId());
                existingProject.setTeamId(updatedProject.getTeamId());
                existingProject.setVisibility(updatedProject.getVisibility());
            } else {
                if (updatedProject.getName() != null) {
                    existingProject.setName(updatedProject.getName());
                }
                if (updatedProject.getAuthorId() != null) {
                    existingProject.setAuthorId(updatedProject.getAuthorId());
                }
                if (updatedProject.getTeamId() != null) {
                    existingProject.setTeamId(updatedProject.getTeamId());
                }
                if (updatedProject.getVisibility() != null) {
                    existingProject.setVisibility(updatedProject.getVisibility());
                }
            }
            return projectRepository.save(existingProject);
        } else {
            logger.warn("Project with ID {} not found for update", id);
            return null;
        }
    }

    @Override
    public void deleteProject(UUID id) {
        logger.info("Deleting project with ID: {}", id);
        projectRepository.deleteById(id);
    }

    @Override
    public void transferProjectToUser(UUID projectId, UUID newOwnerId) {
        logger.info("Transferring project {} to user {}", projectId, newOwnerId);
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isPresent()) {
            Project project = projectOpt.get();
            project.setAuthorId(newOwnerId); // Простая заглушка
            projectRepository.save(project);
        } else {
            logger.warn("Project with ID {} not found for transfer", projectId);
        }
    }

}
