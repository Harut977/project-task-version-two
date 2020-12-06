package com.task.api.contact.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

    private Long id;

    private Long projectId;

    private String contact;

    private String email;

    private String phone;

    private Date createdDate;

    private Date lastModifiedDate;
}
