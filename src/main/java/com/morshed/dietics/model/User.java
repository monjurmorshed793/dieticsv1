package com.morshed.dietics.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
  @Id
  private String id;
  @NotNull
  @Indexed(unique = true)
  private String username;
  @NotNull
  private String password;
  @NotNull
  @Indexed(unique = true)
  private String email;
  @NotNull
  private Boolean isActive;
}
