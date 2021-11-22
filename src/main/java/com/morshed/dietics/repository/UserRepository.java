package com.morshed.dietics.repository;

import com.morshed.dietics.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
