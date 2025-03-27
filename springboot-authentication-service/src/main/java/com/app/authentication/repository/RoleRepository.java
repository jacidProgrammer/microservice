package com.app.authentication.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.authentication.models.entity.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long>{

}
