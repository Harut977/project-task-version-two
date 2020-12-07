package com.task.service.contact.service.impl;

import com.task.api.base.response.AllBaseResponse;
import com.task.api.contact.request.ContactRequest;
import com.task.api.contact.response.ContactResponse;
import com.task.service.base.error.ExceptionMessages;
import com.task.service.contact.entity.ContactEntity;
import com.task.service.contact.mapper.ContactMapper;
import com.task.service.contact.repository.ContactRepository;
import com.task.service.contact.service.ContactService;
import com.task.service.exception.models.NotFoundException;
import com.task.service.exception.models.NotReadException;
import com.task.service.project.entity.ProjectEntity;
import com.task.service.project.repository.ProjectRepository;
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
public class ContactServiceImpl implements ContactService {

    private final ProjectRepository projectRepository;

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Override
    @SneakyThrows
    public ContactResponse addContact(
            final Long id, final ContactRequest request) {
        ProjectEntity projectEntity = projectRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(ExceptionMessages.PROJECT_NOT_FOUND.getMessage()
                                + id, HttpStatus.BAD_REQUEST));
        ContactEntity entity =
                contactMapper.fromContactRequestToContactEntity(projectEntity, request);
        try {
            entity = contactRepository.save(entity);
        } catch (Exception e) {
            LOGGER.warn(
                    ExceptionMessages.CONTACT_NOT_CREATED.getMessage() + "->"
                            + e.getMessage());
            throw new
                    NotReadException(
                    ExceptionMessages.CONTACT_NOT_CREATED.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return contactMapper.fromContactEntityToContactResponse(entity);
    }

    @Override
    @SneakyThrows
    public boolean updateContactById(final Long id, final ContactRequest request) {
        if (!contactRepository.existsById(id)) {
            throw new NotFoundException(
                    ExceptionMessages.CONTACT_NOT_FOUND.getMessage() + id,
                    HttpStatus.BAD_REQUEST);
        }
        try {
            contactRepository.updateContactNative(id, request.getContact(), request.getEmail(), request.getPhone());
            return true;
        } catch (Exception e) {
            LOGGER.warn(
                    ExceptionMessages.CONTACT_NOT_UPDATED.getMessage() + "->"
                            + e.getMessage());
            throw new
                    NotReadException(
                    ExceptionMessages.CONTACT_NOT_UPDATED.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @SneakyThrows
    public ContactResponse getContactById(final Long id) {
        ContactEntity entity = contactRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(ExceptionMessages.CONTACT_NOT_FOUND.getMessage()
                                + id, HttpStatus.BAD_REQUEST));
        return contactMapper.fromContactEntityToContactResponse(entity);

    }

    @Override
    @SneakyThrows
    public boolean deleteContactById(final Long id) {
        if (!contactRepository.existsById(id)) {
            throw new NotFoundException(ExceptionMessages.CONTACT_NOT_FOUND.getMessage()
                    + id, HttpStatus.BAD_REQUEST);
        }
        try {
            contactRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            LOGGER.warn(
                    ExceptionMessages.CONTACT_NOT_DELETED.getMessage() + "->"
                            + e.getMessage());
            throw new
                    NotReadException(
                    ExceptionMessages.CONTACT_NOT_DELETED.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public AllBaseResponse<ContactResponse> getAllContacts(final Integer pageNumber,
                                                           final int pageSize,
                                                           final Long projectId,
                                                           final String sortBy,
                                                           final String sortOrder) {
        AllBaseResponse<ContactResponse> allProjects = new AllBaseResponse<>();
        List<ContactEntity> contactResponses;
        long count;
        contactResponses = contactRepository.getAllByIdNative(projectId, (
                PageRequest.of(pageNumber, pageSize,
                        Sort.by(Sort.Direction.fromString(sortOrder), sortBy))));
        count = contactRepository.countByIdNative(projectId);
        allProjects.setCount(count);
        allProjects.setObjects(
                contactMapper.fromContactEntityListToContactResponseList(contactResponses));
        return allProjects;
    }
}
