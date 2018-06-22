package org.apache.ambari.server.security.authentication;

import java.util.Collection;

import org.apache.ambari.server.security.authorization.AmbariGrantedAuthority;
import org.apache.ambari.server.security.authorization.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AmbariUserDetails implements UserDetails {
  private final User user;
  private final Collection<AmbariGrantedAuthority> userAuthorities;

  public AmbariUserDetails(User user, Collection<AmbariGrantedAuthority> userAuthorities) {
    this.user = user;
    this.userAuthorities = userAuthorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return userAuthorities;
  }

  @Override
  public String getPassword() {
    return (user == null) ? null : "";
  }

  @Override
  public String getUsername() {
    return (user == null) ? null : user.getUserName();
  }

  public Integer getUserId() {
    return (user == null) ? null : user.getUserId();
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
    return true;
  }
}
