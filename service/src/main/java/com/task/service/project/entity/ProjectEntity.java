package com.task.service.project.entity;

import com.task.service.base.entity.BaseEntity;
import com.task.service.contact.entity.ContactEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
@EqualsAndHashCode(callSuper = true)
public class ProjectEntity extends BaseEntity {

    private String title;

    private int status;

    @Cascade(CascadeType.DELETE)
    @OneToMany(mappedBy = "projectEntity")
    private List<ContactEntity> contacts;
}
