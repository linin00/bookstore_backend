package xyz.linin.bookstore_backend.entity;

import io.swagger.annotations.ApiModel;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@ApiModel("用户认证信息")
public class AuthUser implements UserDetails {
    private Integer id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private Boolean enabled;

    public AuthUser(User user) {
        id = user.getId();
        username = user.getName();
        password = user.getPassword();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        this.authorities = authorities;
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
        return this.enabled;
    }
}
