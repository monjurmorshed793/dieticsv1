package com.morshed.dietics.repository;

import com.morshed.dietics.model.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {
  List<UserRole> findAllByUser_Id(String userId);
}
