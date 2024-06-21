package com.poscodx.mysite.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.poscodx.mysite.vo.UserVo;

public class UserDetailsImpl extends UserVo implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return super.getPassword(); // 자식, 부모 둘에게 있기에 부모인 UserDetails getPassword()를 부르기 위함
  }

  @Override
  public String getUsername() { // username = email달라는 뜻
    return getEmail();
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
