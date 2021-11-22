package com.morshed.dietics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@Getter
@Setter
public class UserRole {
  @Id
  private String id;
  @DBRef
  private User user;
  @DBRef
  private Role role;
}
