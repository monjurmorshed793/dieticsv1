package com.morshed.dietics.controllers.security;

import com.morshed.dietics.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class TokenController {

  @Autowired
  JwtEncoder encoder;

  @GetMapping("/token")
  public TokenDTO token(Authentication authentication) {
    Instant now = Instant.now();
    long expiry = 36000L;
    // @formatter:off
    String scope = authentication.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(" "));
    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuer("self")
      .issuedAt(now)
      .expiresAt(now.plusSeconds(expiry))
      .subject(authentication.getName())
      .claim("scope", scope)
      .build();
    // @formatter:on
    return new TokenDTO(this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
  }
}
