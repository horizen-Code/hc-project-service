package com.horizen.hc_project_service.dto;

import java.util.UUID;

import com.horizen.hc_project_service.model.Visibility;

public class CreateProjectRequest {
    private String name;
    private UUID authorId;
    private UUID teamId;
    private Visibility visibility;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}