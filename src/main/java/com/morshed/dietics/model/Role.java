package com.morshed.dietics.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {
  @Id
  private String id;
  private String name;
}
