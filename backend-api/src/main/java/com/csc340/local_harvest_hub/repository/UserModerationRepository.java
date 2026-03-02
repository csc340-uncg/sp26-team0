package com.csc340.local_harvest_hub.repository;

import com.csc340.local_harvest_hub.entity.UserModeration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserModerationRepository extends JpaRepository<UserModeration, Long> {
  @Query(value = "SELECT um FROM UserModeration um WHERE um.target_user_id = :userId", nativeQuery = true)
    List<UserModeration> findByTargetUserId(Long userId);

    @Query(value = "SELECT um FROM UserModeration um WHERE um.admin_id = :adminId", nativeQuery = true)
    List<UserModeration> findByAdminId(Long adminId);
}
