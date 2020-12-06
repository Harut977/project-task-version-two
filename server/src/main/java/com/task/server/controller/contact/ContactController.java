package com.task.server.controller.contact;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.base.response.BaseResponse;
import com.task.api.contact.ContactApi;
import com.task.api.contact.request.ContactRequest;
import com.task.api.contact.response.ContactResponse;
import com.task.service.contact.service.ContactService;
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
public class ContactController implements ContactApi {


    private final ContactService contactService;

    private final RequestFieldsValidator validator;

    @Override
    public ResponseEntity<BaseResponse<ContactResponse>>
    addContact(Long id, @Valid ContactRequest request, Errors errors) {
        validator.validate(request);

        validator.validate(errors);

        ContactResponse contactResponse = contactService.addContact(id, request);

        BaseResponse<ContactResponse> response =
                new BaseResponse<>(true, "ok", contactResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<Boolean>> updateContactById(Long id, @Valid ContactRequest request, Errors errors) {
        validator.validate(request);

        validator.validate(errors);
        String updatedMessage;
        boolean updated = contactService.updateContactById(id, request);
        updatedMessage = (updated) ? "Contact updated successfully"
                : "Contact not updated";

        BaseResponse<Boolean> response =
                new BaseResponse<>(updated, updatedMessage, updated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<ContactResponse>> getContactById(Long id) {
        ContactResponse contactResponse = contactService.getContactById(id);

        BaseResponse<ContactResponse> response =
                new BaseResponse<>(true, "ok", contactResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<Boolean>> deleteContactById(Long id) {
        String deletedMessage;

        boolean deleted = contactService.deleteContactById(id);
        deletedMessage = (deleted) ? "Contact deleted successfully"
                : "Contact not deleted";

        BaseResponse<Boolean> response =
                new BaseResponse<>(deleted, deletedMessage, deleted);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<AllBaseResponse<ContactResponse>>>
    getAllContacts(@Min(value = 0, message = "page number has to be more than or equal to 0") Integer pageNumber,
                   @Min(value = 0, message = "page size has to be more than or equal to 0") int pageSize,
                   Long projectId, String sortBy, String sortOrder) {
        AllBaseResponse<ContactResponse> projectResponses = contactService
                .getAllContacts(pageNumber, pageSize, projectId, sortBy, sortOrder);

        BaseResponse<AllBaseResponse<ContactResponse>> response =
                new BaseResponse<>(true, "ok", projectResponses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
