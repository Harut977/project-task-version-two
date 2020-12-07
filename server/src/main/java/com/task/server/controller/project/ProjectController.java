package com.task.server.controller.project;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.base.response.BaseResponse;
import com.task.api.project.ProjectApi;
import com.task.api.project.request.ProjectRequest;
import com.task.api.project.response.ProjectResponse;
import com.task.service.base.response.ResponseMessages;
import com.task.service.project.service.ProjectService;
import com.task.service.validator.RequestFieldsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;

    private final RequestFieldsValidator validator;

    @Override
    public ResponseEntity<BaseResponse<ProjectResponse>> addProject(
            @Valid final ProjectRequest requests, Errors errors) {

        validator.validate(requests);

        validator.validate(errors);

        ProjectResponse projectResponse = projectService.addProject(requests);

        BaseResponse<ProjectResponse> response =
                new BaseResponse<>(true, ResponseMessages.CREATED.getMessage(), projectResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<Boolean>>
    updateProjectById(final Long id, @Valid final ProjectRequest requests, Errors errors) {
        validator.validate(requests);

        validator.validate(errors);
        String updatedMessage;
        boolean updated = projectService.updateProjectById(id, requests);
        updatedMessage = (updated) ? ResponseMessages.UPDATED.getMessage()
                : ResponseMessages.NOT_UPDATED.getMessage();

        BaseResponse<Boolean> response =
                new BaseResponse<>(updated, updatedMessage, updated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<ProjectResponse>> getProjectById(final Long id) {
        ProjectResponse projectResponse = projectService.getProjectById(id);

        BaseResponse<ProjectResponse> response =
                new BaseResponse<>(
                        true, ResponseMessages.GET.getMessage(), projectResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<Boolean>> deleteProjectById(final Long id) {

        String deletedMessage;

        boolean deleted = projectService.deletedProjectById(id);
        deletedMessage = (deleted) ? ResponseMessages.DELETED.getMessage()
                : ResponseMessages.NOT_DELETED.getMessage();

        BaseResponse<Boolean> response =
                new BaseResponse<>(deleted, deletedMessage, deleted);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<BaseResponse<AllBaseResponse<ProjectResponse>>>
    getAllProjects(
            @Min(value = 0, message = "page number has to be more than or equal to 0") final Integer pageNumber,
            @Min(value = -1, message = "status has to be more than or equal to -1") final int status,
            @Min(value = 0, message = "page size has to be more than or equal to 0") final int pageSize,
            final String title,
            final String sortBy,
            final String sortOrder) {

        AllBaseResponse<ProjectResponse> projectResponses = projectService
                .getAllProjects(pageNumber, status, pageSize, title, sortBy, sortOrder);

        BaseResponse<AllBaseResponse<ProjectResponse>> response =
                new BaseResponse<>(true,
                        ResponseMessages.GET.getMessage(), projectResponses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
