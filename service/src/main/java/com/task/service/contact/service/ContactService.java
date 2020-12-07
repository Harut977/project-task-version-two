package com.task.service.contact.service;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.contact.request.ContactRequest;
import com.task.api.contact.response.ContactResponse;

public interface ContactService {
    ContactResponse addContact(final Long id, final ContactRequest request);

    boolean updateContactById(final Long id, final ContactRequest request);

    ContactResponse getContactById(final Long id);

    boolean deleteContactById(final Long id);

    AllBaseResponse<ContactResponse> getAllContacts(
            final Integer pageNumber,
            final int pageSize,
            final Long projectId,
            final String sortBy,
            final String sortOrder);
}
