package com.horizen.hc_project_service.dto;

import java.util.UUID;

import com.horizen.hc_project_service.model.Visibility;

import lombok.Data;

@Data
public class UpdateProjectRequest {
    private String name;
    private UUID authorId;
    private UUID teamId;
    private Visibility visibility;
}
