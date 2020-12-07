package com.task.server.controller.contact;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.base.response.BaseResponse;
import com.task.api.contact.ContactApi;
import com.task.api.contact.request.ContactRequest;
import com.task.api.contact.response.ContactResponse;
import com.task.service.base.response.ResponseMessages;
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
    addContact(final Long id, @Valid final ContactRequest request, Errors errors) {
        validator.validate(request);

        validator.validate(errors);

        ContactResponse contactResponse = contactService.addContact(id, request);

        BaseResponse<ContactResponse> response =
                new BaseResponse<>(true,
                        ResponseMessages.CREATED.getMessage(), contactResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<Boolean>> updateContactById(
            final Long id, @Valid final ContactRequest request, Errors errors) {
        validator.validate(request);

        validator.validate(errors);
        String updatedMessage;
        boolean updated = contactService.updateContactById(id, request);
        updatedMessage = (updated) ? ResponseMessages.UPDATED.getMessage()
                : ResponseMessages.NOT_UPDATED.getMessage();

        BaseResponse<Boolean> response =
                new BaseResponse<>(updated, updatedMessage, updated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<ContactResponse>> getContactById(final Long id) {
        ContactResponse contactResponse = contactService.getContactById(id);

        BaseResponse<ContactResponse> response =
                new BaseResponse<>(true,
                        ResponseMessages.GET.getMessage(),
                        contactResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<Boolean>> deleteContactById(final Long id) {
        String deletedMessage;

        boolean deleted = contactService.deleteContactById(id);
        deletedMessage = (deleted) ? ResponseMessages.DELETED.getMessage()
                : ResponseMessages.NOT_DELETED.getMessage();

        BaseResponse<Boolean> response =
                new BaseResponse<>(deleted, deletedMessage, deleted);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<AllBaseResponse<ContactResponse>>>
    getAllContacts(@Min(value = 0, message = "page number has to be more than or equal to 0") final Integer pageNumber,
                   @Min(value = 0, message = "page size has to be more than or equal to 0") final int pageSize,
                   final Long projectId,
                   final String sortBy,
                   final String sortOrder) {
        AllBaseResponse<ContactResponse> projectResponses = contactService
                .getAllContacts(pageNumber, pageSize, projectId, sortBy, sortOrder);

        BaseResponse<AllBaseResponse<ContactResponse>> response =
                new BaseResponse<>(true, ResponseMessages.GET.getMessage(), projectResponses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
