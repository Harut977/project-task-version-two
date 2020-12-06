package com.task.service.project.service;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.project.request.ProjectRequest;
import com.task.api.project.response.ProjectResponse;

public interface ProjectService {

    ProjectResponse addProject(final ProjectRequest requests);

    boolean updateProjectById(final Long id, final ProjectRequest requests);

    ProjectResponse getProjectById(final Long id);

    boolean deletedProjectById(final Long id);

    AllBaseResponse<ProjectResponse> getAllProjects(final Integer pageNumber,
                                                    final int status,
                                                    final int pageSize,
                                                    final String title,
                                                    final String sortBy,
                                                    final String sortOrder);
}
