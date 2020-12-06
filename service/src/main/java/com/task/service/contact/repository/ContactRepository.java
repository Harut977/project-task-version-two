package com.task.service.contact.repository;

import com.task.api.contact.response.ContactResponse;
import com.task.service.contact.entity.ContactEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ContactEntity e SET e.contact =?2 , e.email = ?3 , e.phone = ?4 WHERE e.id = ?1 ")
    void updateContactNative(Long id, String contact, String email, String phone);

    @Query("SELECT e FROM ContactEntity e WHERE  e.projectEntity.id = ?1 ")
    List<ContactEntity> getAllByIdNative(Long projectId, PageRequest of);

    @Query("SELECT COUNT(e) FROM ContactEntity e WHERE  e.id = ?1 ")
    long countByIdNative(Long projectId);

}
