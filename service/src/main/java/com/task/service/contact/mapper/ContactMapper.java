package com.task.service.contact.mapper;

import com.task.api.contact.request.ContactRequest;
import com.task.api.contact.response.ContactResponse;
import com.task.service.contact.entity.ContactEntity;
import com.task.service.project.entity.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactMapper {

    private final ModelMapper modelMapper;

    public ContactEntity fromContactRequestToContactEntity(ProjectEntity projectEntity, ContactRequest request) {
        return ContactEntity.builder()
                .contact(request.getContact())
                .projectEntity(projectEntity)
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

    public ContactResponse fromContactEntityToContactResponse(ContactEntity entity) {
        return ContactResponse.builder()
                .contact(entity.getContact())
                .id(entity.getId())
                .lastModifiedDate(entity.getLastModifiedDate())
                .createdDate(entity.getCreatedDate())
                .projectId(entity.getProjectEntity().getId())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();

    }

    public List<ContactResponse> fromContactEntityListToContactResponseList(List<ContactEntity> contactEntities) {
        return modelMapper.map(contactEntities, new TypeToken<List<ContactResponse>>() {
        }.getType());
    }
}
