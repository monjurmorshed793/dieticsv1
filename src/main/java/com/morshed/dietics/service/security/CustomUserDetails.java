package com.morshed.dietics.service.security;

import com.morshed.dietics.model.User;
import com.morshed.dietics.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails extends User implements UserDetails {
  List<UserRole> userRoles;
  User user;
  CustomUserDetails(User user, List<UserRole> userRoles){
    super(user.getId(), user.getFirstName(), user.getLastname(), user.getUsername(), user.getPassword(), user.getEmail(), user.getIsActive());
    this.user = user;
    this.userRoles = userRoles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<String> roles = userRoles.stream().map(r-> r.getRole().getName()).collect(Collectors.toList());
    String[] rolesArr = new String[roles.size()];
    for(int i=0; i<roles.size(); i++){
      rolesArr[i] = roles.get(i);
    }
    return Collections.unmodifiableList(AuthorityUtils.createAuthorityList(rolesArr));
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.getIsActive();
  }
}
