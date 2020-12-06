package com.task.service.project.mapper;

import com.task.api.contact.response.ContactResponse;
import com.task.api.project.request.ProjectRequest;
import com.task.api.project.response.ProjectResponse;
import com.task.service.contact.entity.ContactEntity;
import com.task.service.project.entity.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final ModelMapper modelMapper;

    public ProjectEntity fromProjectRequestToProjectEntity(final ProjectRequest requests) {
        return ProjectEntity.builder()
                .status(requests.getStatus())
                .title(requests.getTitle())

                .build();
    }

    public ProjectResponse fromProjectEntityToProjectResponse(final ProjectEntity entity) {
        return ProjectResponse.builder()
                .id(entity.getId())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .status(entity.getStatus())
                .title(entity.getTitle())
                .contacts(fromContactEntitiesToContactResponse(entity.getContacts()))
                .build();
    }

    private List<ContactResponse> fromContactEntitiesToContactResponse(List<ContactEntity> contactEntities) {
        return modelMapper.map(contactEntities, new TypeToken<List<ContactResponse>>() {
        }.getType());
    }

    public List<ProjectResponse> fromProjectEntityListToProjectResponseList(List<ProjectEntity> projectEntities) {

        return modelMapper.map(projectEntities, new TypeToken<List<ProjectResponse>>() {
        }.getType());
    }
}
