package com.app.authentication.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.authentication.models.entity.User;

@RepositoryRestResource(path="users")
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	public User findByUsername(String username);
}
