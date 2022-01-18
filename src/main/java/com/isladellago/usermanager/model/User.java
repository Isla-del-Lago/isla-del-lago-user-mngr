package com.isladellago.usermanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class User implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column
    @JsonIgnore
    private String password;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Column(name = "creation_date")
    @JsonIgnore
    private final Date creationDate = new Date();

    @Transient
    @JsonIgnore
    private boolean enabled = true;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
