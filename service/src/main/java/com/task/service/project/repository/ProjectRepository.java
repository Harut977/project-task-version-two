package com.task.service.project.repository;

import com.task.service.project.entity.ProjectEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    @Query("SELECT COUNT(e) FROM ProjectEntity e WHERE e.status=?1 and e.title LIKE CONCAT('%',?2,'%') ")
    long countByStatusAndTitleNative(int status, String title);

    @Query("SELECT COUNT(e) FROM ProjectEntity e WHERE  e.title LIKE CONCAT('%',?1,'%') ")
    long countByTitleNative(String title);

    @Query(value = "SELECT e FROM ProjectEntity e where  e.title LIKE CONCAT('%',?1,'%') ")
    List<ProjectEntity> getAllByTitleNative(String title, PageRequest of);

    @Query(value = "SELECT e FROM ProjectEntity e  where e.status = ?1 and  e.title LIKE CONCAT('%',?2,'%') ")
    List<ProjectEntity> getAllByStatusAndTitleNative(int status, String title, PageRequest of);

    @Modifying
    @Transactional
    @Query("UPDATE ProjectEntity e SET e.title =?2 ,e.status = ?3 where e.id =?1 ")
    void updateProjectNative(Long id, String title, int status);

}
