package com.task.service.project.service.impl;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.project.request.ProjectRequest;
import com.task.api.project.response.ProjectResponse;
import com.task.service.base.error.ExceptionMessages;
import com.task.service.exception.models.NotFoundException;
import com.task.service.exception.models.NotReadException;
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
    public ProjectResponse addProject(final ProjectRequest requests) {
        ProjectEntity entity =
                projectMapper.fromProjectRequestToProjectEntity(requests);
        try {
            entity = projectRepository.save(entity);

        } catch (Exception e) {
            LOGGER.warn(
                    ExceptionMessages.PROJECT_NOT_CREATED.getMessage() + "->"
                            + e.getMessage());
            throw new
                    NotReadException(
                    ExceptionMessages.PROJECT_NOT_CREATED.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return projectMapper.fromProjectEntityToProjectResponse(entity);
    }

    @Override
    @SneakyThrows
    public boolean updateProjectById(final Long id, final ProjectRequest requests) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException(
                    ExceptionMessages.PROJECT_NOT_FOUND.getMessage() + id,
                    HttpStatus.BAD_REQUEST);
        }
        try {
            projectRepository
                    .updateProjectNative(id, requests.getTitle(), requests.getStatus());
            return true;
        } catch (Exception e) {
            LOGGER.warn(
                    ExceptionMessages.PROJECT_NOT_UPDATED.getMessage() + "->"
                            + e.getMessage());
            throw new
                    NotReadException(
                    ExceptionMessages.PROJECT_NOT_UPDATED.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @SneakyThrows
    public ProjectResponse getProjectById(final Long id) {
        ProjectEntity entity = projectRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                ExceptionMessages.PROJECT_NOT_FOUND.getMessage()
                                        + id, HttpStatus.BAD_REQUEST));
        return projectMapper.fromProjectEntityToProjectResponse(entity);
    }

    @Override
    @SneakyThrows
    public boolean deletedProjectById(final Long id) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException(ExceptionMessages.PROJECT_NOT_FOUND.getMessage()
                    + id, HttpStatus.BAD_REQUEST);
        }
        try {
            projectRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            LOGGER.warn(
                    ExceptionMessages.PROJECT_NOT_DELETED.getMessage() + "->"
                            + e.getMessage());
            throw new
                    NotReadException(
                    ExceptionMessages.PROJECT_NOT_UPDATED.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public AllBaseResponse<ProjectResponse> getAllProjects(
            final Integer pageNumber,
            final int status,
            final int pageSize,
            final String title,
            final String sortBy,
            final String sortOrder) {

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
        allProjects.setObjects(projectMapper
                .fromProjectEntityListToProjectResponseList(projectEntities));

        return allProjects;

    }
}
