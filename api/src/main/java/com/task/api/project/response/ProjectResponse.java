package com.task.api.project.response;

import com.task.api.contact.response.ContactResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private Long id;

    private String title;

    private int status;

    private List<ContactResponse> contacts;

    private Date createdDate;

    private Date lastModifiedDate;

}
