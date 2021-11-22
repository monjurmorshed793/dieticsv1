package com.morshed.dietics.migration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.morshed.dietics.model.Role;
import com.morshed.dietics.model.User;
import com.morshed.dietics.model.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@ChangeLog(order = "00001")
@Slf4j
public class DbChangelog00001 {
  @ChangeSet(order = "001", id="seedUserRoles", author = "Monjur-E-Morshed")
  public void seedUserRoles(MongoTemplate mongoTemplate){
    Role userRole = Role.builder()
      .name("ROLE_USER")
      .build();
    userRole = mongoTemplate.insert(userRole);
    Role dieticianRole = Role.builder()
      .name("DIETICIAN")
      .build();
    dieticianRole = mongoTemplate.insert(dieticianRole);
    Role adminRole = Role.builder()
      .name("ADMIN")
      .build();
    adminRole = mongoTemplate.insert(adminRole);

    User regularUser = User.builder()
        .username("user")
          .password(new BCryptPasswordEncoder().encode("user"))
            .email("user@mail.com")
              .isActive(true)
                .build();
    regularUser = mongoTemplate.insert(regularUser);

    User dieticianUser = User.builder()
        .username("dietician")
          .password(new BCryptPasswordEncoder().encode("dietician"))
            .email("dietician@mail.com")
              .isActive(true)
                .build();
    dieticianUser = mongoTemplate.insert(dieticianUser);

    User adminUser = User
      .builder()
        .username("admin")
          .password(new BCryptPasswordEncoder().encode("admin"))
            .isActive(true)
              .email("admin@mail.com")
                .build();
    adminUser = mongoTemplate.insert(adminUser);

    mongoTemplate.insertAll(Arrays.asList(
      UserRole.builder().user(regularUser).role(userRole).build(),
      UserRole.builder().user(dieticianUser).role(dieticianRole).build(),
      UserRole.builder().user(adminUser).role(adminRole).build()
    ));

    log.debug("DB Migrations- Changelog 00001, ChangeSet 001");
  }
}
