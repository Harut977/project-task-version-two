package com.task.api.contact;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.base.response.BaseResponse;
import com.task.api.contact.request.ContactRequest;
import com.task.api.contact.response.ContactResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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

@Validated
@RequestMapping("/contact")
public interface ContactApi {

    @PostMapping("/{project_id}")
    ResponseEntity<BaseResponse<ContactResponse>> addContact(
            @PathVariable(name = "project_id") Long projectId,
            @RequestBody @Valid ContactRequest request, Errors errors);

    @PutMapping("/{id}")
    ResponseEntity<BaseResponse<Boolean>> updateContactById(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid ContactRequest request, Errors errors);

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse<ContactResponse>> getContactById(
            @PathVariable(name = "id") Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponse<Boolean>> deleteContactById(
            @PathVariable(name = "id") Long id);

    @GetMapping
    ResponseEntity<BaseResponse<AllBaseResponse<ContactResponse>>>
    getAllContacts(@RequestParam(name = "pageNumber", defaultValue = "0")
                   @Min(value = 0, message = "page number has to be more than or equal to 0") Integer pageNumber,
                   @RequestParam(name = "pageSize", defaultValue = "10")
                   @Min(value = 1, message = "page size has to be more than or equal to 1")
                           int pageSize,
                   @RequestParam(name = "id", defaultValue = "") Long projectId,
                   @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                   @RequestParam(name = "sortOrder", defaultValue = "desc") String sortOrder);


}
