package com.task.service.contact.service;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.contact.request.ContactRequest;
import com.task.api.contact.response.ContactResponse;
import com.task.api.project.response.ProjectResponse;

public interface ContactService {
    ContactResponse addContact(Long id, ContactRequest request);

    boolean updateContactById(Long id, ContactRequest request);

    ContactResponse getContactById(Long id);

    boolean deleteContactById(Long id);

    AllBaseResponse<ContactResponse> getAllContacts(
            Integer pageNumber,
            int pageSize,
            Long projectId, String sortBy, String sortOrder);

}
