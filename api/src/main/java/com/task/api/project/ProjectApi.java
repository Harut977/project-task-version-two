package com.task.api.project;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.base.response.BaseResponse;
import com.task.api.project.request.ProjectRequest;
import com.task.api.project.response.ProjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RequestMapping("/project")
public interface ProjectApi {

    @PostMapping
    ResponseEntity<BaseResponse<ProjectResponse>> addProject(
            @RequestBody @Valid ProjectRequest request, Errors errors);

    @PutMapping("/{id}")
    ResponseEntity<BaseResponse<Boolean>> updateProjectById(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid ProjectRequest request, Errors errors);

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse<ProjectResponse>> getProjectById(
            @PathVariable(name = "id") Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponse<Boolean>> deleteProjectById(
            @PathVariable(name = "id") Long id);

    @GetMapping
    ResponseEntity<BaseResponse<AllBaseResponse<ProjectResponse>>>
    getAllProjects(@RequestParam(name = "pageNumber", defaultValue = "0")
                   @Min(value = 0, message = "page number has to be more than or equal to 0") Integer pageNumber,
                   @RequestParam(name = "status", defaultValue = "-1")
                   @Min(value = -1, message = "status has to be more than or equal to -1")
                           int status,
                   @RequestParam(name = "pageSize", defaultValue = "10")
                   @Min(value = 1, message = "page size has to be more than or equal to 1")
                           int pageSize,
                   @RequestParam(name = "title", defaultValue = "") String title,
                   @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                   @RequestParam(name = "sortOrder", defaultValue = "desc") String sortOrder);
}
