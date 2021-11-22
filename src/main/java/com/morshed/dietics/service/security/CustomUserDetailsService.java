package com.morshed.dietics.service.security;

import com.morshed.dietics.model.User;
import com.morshed.dietics.repository.UserRepository;
import com.morshed.dietics.repository.UserRoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;
  private UserRoleRepository userRoleRepository;

  public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
    this.userRepository = userRepository;
    this.userRoleRepository = userRoleRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    if(user.isEmpty())
      throw new UsernameNotFoundException("username "+username+" is not found");
    return new CustomUserDetails(user.get(), userRoleRepository.findAllByUser_Id(user.get().getId())) ;
  }
}
