package com.morshed.dietics.repository;

import com.morshed.dietics.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
