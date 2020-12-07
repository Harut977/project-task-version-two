package com.task.service.contact.entity;

import com.task.service.base.entity.BaseEntity;
import com.task.service.project.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
@EqualsAndHashCode(callSuper = true)
public class ContactEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity projectEntity;

    private String contact;

    private String email;

    private String phone;
}
