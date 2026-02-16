package com.vlt.indentityservice.repository;

import java.util.Optional;

import com.vlt.indentityservice.dto.request.PermissionRequest;
import com.vlt.indentityservice.dto.response.PermissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vlt.indentityservice.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String>{
    Optional<Permission> findByName(String name);
}
