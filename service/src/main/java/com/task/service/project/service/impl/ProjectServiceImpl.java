package com.task.service.project.service.impl;


import com.task.service.exception.models.NotFoundException;
import com.task.api.base.response.AllBaseResponse;
import com.task.api.project.request.ProjectRequest;
import com.task.api.project.response.ProjectResponse;
import com.task.service.project.entity.ProjectEntity;
import com.task.service.project.mapper.ProjectMapper;
import com.task.service.project.repository.ProjectRepository;
import com.task.service.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Override
    public ProjectResponse addProject(ProjectRequest requests) {
        ProjectEntity entity =
                projectMapper.fromProjectRequestToProjectEntity(requests);
        try {
            entity = projectRepository.save(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectMapper.fromProjectEntityToProjectResponse(entity);
    }

    @Override
    @SneakyThrows
    public boolean updateProjectById(Long id, ProjectRequest requests) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException("Project not found with this id = " + id, HttpStatus.BAD_REQUEST);
        }

        try {
            projectRepository.updateProjectNative(id, requests.getTitle(), requests.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    @Override
    @SneakyThrows
    public ProjectResponse getProjectById(Long id) {
        ProjectEntity entity = projectRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Project not found with this id = "
                                + id, HttpStatus.BAD_REQUEST));
        return projectMapper.fromProjectEntityToProjectResponse(entity);
    }

    @Override
    @SneakyThrows
    public boolean deletedProjectById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException("Project not found with this id = "
                    + id, HttpStatus.BAD_REQUEST);
        }
        try {
            projectRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public AllBaseResponse<ProjectResponse> getAllProjects(
            Integer pageNumber,
            int status,
            int pageSize,
            String title,
            String sortBy,
            String sortOrder) {

        AllBaseResponse<ProjectResponse> allProjects = new AllBaseResponse<>();
        List<ProjectEntity> projectEntities;
        long count;

        if (status == -1) {
            projectEntities = projectRepository.getAllByTitleNative(title, (
                    PageRequest.of(pageNumber, pageSize,
                            Sort.by(Sort.Direction.fromString(sortOrder), sortBy))));
            count = projectRepository.countByTitleNative(title);
        } else {
            projectEntities = projectRepository.getAllByStatusAndTitleNative(status, title, (
                    PageRequest.of(pageNumber, pageSize,
                            Sort.by(Sort.Direction.fromString(sortOrder), sortBy))));

            count = projectRepository.countByStatusAndTitleNative(status, title);
        }

        allProjects.setCount(count);
        allProjects.setObjects(projectMapper.fromProjectEntityListToProjectResponseList(projectEntities));

        return allProjects;

    }
}
