package com.morshed.dietics.repository;

import com.morshed.dietics.model.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {

}
