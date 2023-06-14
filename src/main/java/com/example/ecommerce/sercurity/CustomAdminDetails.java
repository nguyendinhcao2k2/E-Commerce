package com.example.ecommerce.sercurity;

import com.example.ecommerce.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author caodinh
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomAdminDetails implements UserDetails {

    Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(admin.getChucVu().getTen()));
    }

    @Override
    public String getPassword() {
        return admin.getMatKhau();
    }

    @Override
    public String getUsername() {
        return admin.getTaiKhoan();
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
